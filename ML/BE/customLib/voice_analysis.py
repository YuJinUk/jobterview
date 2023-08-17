import os
import numpy as np
import librosa
import librosa.display
import struct
import soundfile as sf
from io import BytesIO
import tempfile
from keras.models import load_model
from sklearn.preprocessing import scale


def process_audio(origin_path, new_path, new_name):
    binary_data = open(origin_path, 'rb').read()
    save_audio_path = new_path
    temp_wav_file = binary_to_temporary_wav(binary_data, directory=save_audio_path, file_name=new_name)
    audio, sr = load_audio(temp_wav_file, new_name)
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

def load_audio(file_path, new_name=None):
    if new_name == None:
        new_name = 'dataset/new_audio.wav'
    audio, sr = librosa.load(file_path, sr=None, res_type='audioread')  # sr=None will keep the original sampling rate
    sf.write('dataset/' + new_name, data=audio, samplerate=sr)
    return audio, sr


def extract_mfcc(filename):
    y, sr = librosa.load(filename, sr = 16000, duration=3, offset=0.5)
    mfcc = librosa.feature.mfcc(y = y, sr = sr, n_mfcc=100, n_fft=400, hop_length=160)
    return mfcc

def preprocess(file_path):
    mfccs = extract_mfcc(file_path)
    pad2d = lambda a, i: a[:, 0:i] if a.shape[1] > i else np.hstack((a, np.zeros((a.shape[0], i-a.shape[1]))))
    mfcc = scale(mfccs, axis=1)
    padded_mfcc = pad2d(mfcc, 320)
    test_preprocessing = np.expand_dims(padded_mfcc, 0)
    return test_preprocessing

def audio_normalization(audio, sr, audio_path):
    result = {
        0 : 'sad',
        1 : 'neutral',
        2 : 'surprise',
        3 : 'happy',
        4 : 'fear',
        5 : 'angry',
        6 : 'disgust',
    }
    basic_model = load_model('customLib/model_male.h5')
    new_audio = preprocess(audio_path)
    result2 = {
        'sad' : 0,
        'neutral' : 0,
        'surprise' : 0,
        'happy': 0,
        'fear': 0,
        'angry': 0,
        'disgust' : 0,
    }
    prediction = basic_model.predict(new_audio)
    
    for idx, p in enumerate(prediction[0]):
        result2[result[idx]] = p
        
    for i in result2:
        result2[i] = 100 * result2[i]/ len(prediction)
    return result2

def female_audio_normalization(audio, sr, audio_path):
    result = {
        0 : 'sad',
        1 : 'neutral',
        2 : 'surprise',
        3 : 'happy',
        4 : 'fear',
        5 : 'angry',
        6 : 'disgust',
    }
    basic_model = load_model('customLib/model_female.h5')
    new_audio = preprocess(audio_path)
    result2 = {
        'sad' : 0,
        'neutral' : 0,
        'surprise' : 0,
        'happy': 0,
        'fear': 0,
        'angry': 0,
        'disgust' : 0,
    }
    prediction = basic_model.predict(new_audio)
    
    for idx, p in enumerate(prediction[0]):
        result2[result[idx]] = p
        
    for i in result2:
        result2[i] = 100 * result2[i]/ len(prediction)
    return result2
