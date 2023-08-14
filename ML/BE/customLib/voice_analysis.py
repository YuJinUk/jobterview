import os
import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import wave
import subprocess
import librosa
import librosa.display
import wave
import struct
import soundfile as sf
from io import BytesIO
from scipy.signal import wiener
import tempfile
from keras.models import load_model
from sklearn.preprocessing import scale

def video2wav(video_path, output = "wav"):
    video_path = './' + video_path
    filename, ext = os.path.splitext(video_path)
    subprocess.call(["ffmpeg", "-y", "-i", video_path, f"{filename}.{output}"],
                    stdout=subprocess.DEVNULL,
                    stderr=subprocess.STDOUT,
                    shell=True)
    
    return f"{filename}.{output}"

DURATION = 10
SAMPLE_RATE = 44100
CHANNEL = 1
BIT_RATE = 32

def create_wav_file(existing_wave_file, output_wave_file):
    with open(existing_wave_file, 'rb') as existing_file:
        # 기존 Wave 파일의 데이터를 읽어옵니다
        data = existing_file.read()

    # RIFF 헤더 생성
    chunk_id = b'RIFF'
    chunk_size = len(data) + 36
    format_id = b'WAVEfmt'

    # RIFF 헤더를 이진 데이터로 패킹합니다
    riff_header = struct.pack('<4sI4s', chunk_id, chunk_size, format_id)

    # 새로운 Wave 파일 생성하고 RIFF 헤더와 데이터를 씁니다
    with open(output_wave_file, 'wb') as output_file:
        output_file.write(riff_header)
        output_file.write(data)

    print('새로운 Wave 파일이 생성되었습니다.')
# # WAV 파일 생성
# create_wav_file('audio_data.wav')

def read_wav_without_riff_header(filename):
    with wave.open(filename, 'rb') as wav:
        # WAV 파일의 속성 정보 읽기
        sample_width = wav.getsampwidth()
        channels = wav.getnchannels()
        sample_rate = wav.getframerate()
        frames = wav.getnframes()

        # WAV 데이터 읽기
        data = wav.readframes(frames)

    return sample_rate, data

def read_wav_with_soundfile(filename):
    with open(filename, 'rb') as f:
        # WAV 헤더 읽기
        riff_header = f.read(12)
        fmt_chunk_header = f.read(8)
        data_chunk_header = f.read(8)

        # 샘플링 속도 추출
        sample_rate = struct.unpack('<I', fmt_chunk_header[4:8])[0]

        # 데이터 크기 추출
        data_size = struct.unpack('<I', data_chunk_header[4:8])[0]

        # 오디오 데이터 추출
        audio_data = f.read(data_size)

    # 오디오 데이터를 넘파이 배열로 변환
    audio_array = np.frombuffer(audio_data, dtype=np.int16)

    return audio_array, sample_rate



def process_audio(origin_path, new_path, new_name):
    binary_data = open(origin_path, 'rb').read()
    save_audio_path = new_path
    temp_wav_file = binary_to_temporary_wav(binary_data, directory=save_audio_path, file_name=new_name)
    print(temp_wav_file)
    print('----------------------------------------------------------------------------------------')
    print('읽기 전까지 옴')
    print('----------------------------------------------------------------------------------------')
    audio, sr = load_audio(temp_wav_file, new_name)
    print(audio, sr)
    os.remove(temp_wav_file)  # Remove the temporary WAV file
    return audio, sr

def binary_to_temporary_wav(binary_data, directory=None, file_name=None):
    if file_name is None:
        file_name = "dataset/new_audio.wav"
        
    with tempfile.NamedTemporaryFile(suffix=".wav", delete=False, dir=directory, prefix=file_name) as temp_wav:
        temp_wav.write(binary_data)
        temp_wav.seek(0)
        return temp_wav.name

def numpy_to_binary(numpy_data):
    buffer = BytesIO()
    numpy_data.export(buffer, format="wav")
    return buffer.getvalue()

def remove_noise(audio):
    return wiener(audio)

def load_audio(file_path, new_name=None):
    if new_name == None:
        new_name = 'dataset/new_audio.wav'
    audio, sr = librosa.load(file_path, sr=None, res_type='audioread')  # sr=None will keep the original sampling rate
    sf.write('dataset/' + new_name, data=audio, samplerate=sr)
    return audio, sr


def extract_mfcc(filename):
    y, sr = librosa.load(filename, duration=3, offset=0.5)
    # mfcc = np.mean(librosa.feature.mfcc(y=audio, sr=sr, n_mfcc=50).T, axis=0) # n_mfcc : number of features 
    mfcc = librosa.feature.mfcc(y = y, sr = sr, n_mfcc=100, n_fft=400, hop_length=160)
    return mfcc

def preprocess(file_path):
    # test_mfcc = extract_mfcc(file_path)
    # test = [x for x in test_mfcc]
    # test = np.array(test)
    # test = np.expand_dims(test, -1)
    mfcc = extract_mfcc(file_path)
    mfcc = scale(mfcc, axis=1)
    pad2d = lambda a, i: a[:, 0:i] if a.shape[1] > i else np.hstack((a, np.zeros((a.shape[0], i-a.shape[1]))))
    padded_mfcc = pad2d(mfcc, 160)
    test_preprocessing = np.expand_dims(padded_mfcc, 0)
    return test_preprocessing

def audio_normalization(audio, sr, audio_path):
    result = {
        0 : 'sad',
        1 : 'neutral',
        2 : 'fear',
        3 : 'angry',
        4 : 'disgust',
    }
    result2 = {
        'sad' : 0,
        'neutral' : 0,
        'fear': 0,
        'angry': 0,
        'disgust' : 0,
    }
    
    basic_model = load_model('customLib/model_male.h5')
    new_audio = preprocess(audio_path)
    prediction = basic_model.predict(new_audio)
    
    print(prediction)
    
    for idx, p in enumerate(prediction[0]):
        result2[result[idx]] = p
        
    for i in result2:
        result2[i] = 100 * result2[i]/ len(prediction)
    
    return result2


def female_audio_normalization(audio, sr, audio_path):
    result = {
        0 : 'sad',
        1 : 'neutral',
        2 : 'fear',
        3 : 'angry',
        4 : 'disgust',
    }
    result2 = {
        'sad' : 0,
        'neutral' : 0,
        'fear': 0,
        'angry': 0,
        'disgust' : 0,
    }
    
    basic_model = load_model('customLib/model_female.h5')
    new_audio = preprocess(audio_path)
    prediction = basic_model.predict(new_audio)
    
    for idx, p in enumerate(prediction[0]):
        result2[result[idx]] = p
        
    for i in result2:
        result2[i] = 100 * result2[i]/ len(prediction)
    
    return result2