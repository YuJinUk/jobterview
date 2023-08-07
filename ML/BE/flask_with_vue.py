from flask import Flask, request, jsonify
from flask_cors import CORS, cross_origin
from concurrent.futures import ThreadPoolExecutor
from customLib.deepface_video import facial_expression_and_eye_movements
from customLib.voice_analysis import process_audio, audio_normalization
import numpy as np
import os
from scipy.io import wavfile
import matplotlib.pyplot as plt
import speech_recognition

app = Flask(__name__)
# CORS(app)
cors = CORS(app, resources={r"/api/*": {"origins": "*"}})

executor1 = ThreadPoolExecutor(10)
executor2 = ThreadPoolExecutor(10)
executor3 = ThreadPoolExecutor(10)
executor4 = ThreadPoolExecutor(10)


def process_video_and_audio(video_data, audio_info, num):
    
    with open(f'dataset/video_data{num}.mp4', 'wb') as f:
        f.write(video_data)
    
    result_emotion, result_eye = facial_expression_and_eye_movements(f'dataset/video_data{num}.mp4')
    print(result_emotion)
    print(result_eye)
    result_emotion['eye_movement'] = result_eye
    
    # remove video in os folder
    video_path = f'dataset/video_data{num}.mp4'
    if os.path.exists(video_path):
        os.remove(video_path)
        
    audio, sr, audio_path = audio_info['audio_data'], audio_info['sample_rate'], audio_info['audio_path']
    
    SER = audio_normalization(audio, sr, audio_path)
    
    result_emotion['SER'] = SER
    
    audio_STT = speech_recognition.Recognizer()
        
    audio_file = speech_recognition.AudioFile(audio_path)

    with audio_file as source:
        audio_text = audio_STT.record(source)
        
    text = audio_STT.recognize_google(audio_text, language='ko-KR', show_all=True)
    print(text)
    if text:
        result_emotion['STT_message'] = text['alternative'][0]['transcript']
    else:
        result_emotion['STT_message'] = '아무 말도 하지 않음'
    return result_emotion

@app.route('/api')
def home():
    return "okay"

@app.route('/api/upload/1', methods=['POST'])
@cross_origin(origin='*localhost',headers=['Content- Type','Authorization','video/x-matroska;codecs=avc1', 'audio/ogg codecs=opus', 'audio/wav'])
def upload1():
    if 'video' in request.files and 'audio' in request.files:
        video_file = request.files.get('video')
        video_data = video_file.read()
        video_file.seek(0)
        # print(video_file)
        # video_data = base64.b64encode(video_data)
        """ 
        video_data(encoding base64) from flask to DB
        
        """ 
        # video_data = base64.b64decode(video_data)
        """
        video_data(decoding base64) from DB to flask
        
        """
        # save video in os folder
        # with open('video_data.mp4', 'wb') as f:
        #     f.write(video_data)
        
        """
        Analyze facial expressions for 7 emotions 
        and 
        check eye movements(left and right)
        """
        
        # result_emotion, result_eye = facial_expression_and_eye_movements('video_data.mp4')
        # print(result_emotion)
        # print(result_eye)
        # result_emotion['eye_movement'] = result_eye
        
        # # remove video in os folder
        # video_path = 'video_data.mp4'
        # if os.path.exists(video_path):
        #     os.remove(video_path)
        
        # audio_analysis
        audio_file = request.files.get('audio')
        # print(audio_file)
        audio_file.save('dataset/audio_data1.wav') # binary data type
        audio, sr = process_audio('dataset/audio_data1.wav', './', 'new_audio1.wav')
        # print(audio, sr)
        
        # plt.figure(figsize=(10, 12))  # 전체 그림 크기를 조정
        # plt.subplot(211)
        # plt.plot(audio)
        # plt.title('Audio Waveform')
        # plt.xlabel('Sample')
        # plt.ylabel('Amplitude')
        # print('sr:', sr, ', audio shape:', audio.shape)
        # print('length:', audio.shape[0]/float(sr), 'secs')
        
        
        # audio_float = audio.astype(float) / np.abs(audio).max()

        # Calculate the spectrogram using librosa.stft
        # spectrogram = librosa.stft(audio_float)

        # plt.subplot(212)
        # fig, ax = plt.subplots()
        # Spectrogram plotting
        # img = librosa.display.specshow(librosa.amplitude_to_db(spectrogram, ref=np.max),
        #                         y_axis='log', x_axis='time', sr=sr)
        # plt.colorbar(img, format='%+2.0f dB')
        # plt.title('Spectrogram')
        # plt.xlabel('Time (s)')
        # plt.ylabel('Frequency (Hz)')
        # plt.tight_layout()
        # plt.show()
        
        
        # analyze audio_file
        """
        add analysis function about audio_file
        
        """
        # remove audio in os folder
        # audio_path = 'audio_data.wav'
        # if os.path.exists(audio_path):
        #     os.remove(audio_path)
        audio_info = {
            'sample_rate': sr,  # 샘플 레이트 등의 정보
            'audio_data': audio,  # 오디오 데이터
            'audio_path' : 'dataset/new_audio1.wav'
        }
        # 비동기로 처리하기 위해 executor.submit을 사용하여 함수를 실행
        future = executor1.submit(process_video_and_audio, video_data, audio_info, 1)        

        result = future.result()
        return jsonify({'result_emotion' : result})
    else:
        return 'No video or audio file found'

@app.route('/api/upload/2', methods=['POST'])
@cross_origin(origin='*localhost',headers=['Content- Type','Authorization','video/x-matroska;codecs=avc1', 'audio/ogg codecs=opus', 'audio/wav'])
def upload2():
    if 'video' in request.files and 'audio' in request.files:
        video_file = request.files.get('video')
        video_data = video_file.read()
        video_file.seek(0)
        
        # audio_analysis
        audio_file = request.files.get('audio')
        # audio_file.save('dataset/audio_data2.wav') # binary data type
        audio_data = audio_file.read()
        with open('dataset/audio_data1.wav', 'wb') as f:
            f.write(audio_data)
        audio, sr = process_audio('dataset/audio_data2.wav', './', 'new_audio2.wav')
        
        audio_info = {
            'sample_rate': sr,  # 샘플 레이트 등의 정보
            'audio_data': audio,  # 오디오 데이터
            'audio_path' : 'dataset/new_audio2.wav'
        }
        # 비동기로 처리하기 위해 executor.submit을 사용하여 함수를 실행
        future = executor2.submit(process_video_and_audio, video_data, audio_info, 2)       

        result = future.result()
        return jsonify({'result_emotion' : result})
    else:
        return 'No video or audio file found'
    
@app.route('/api/upload/3', methods=['POST'])
@cross_origin(origin='*localhost',headers=['Content- Type','Authorization','video/x-matroska;codecs=avc1', 'audio/ogg codecs=opus', 'audio/wav'])
def upload3():
    if 'video' in request.files and 'audio' in request.files:
        video_file = request.files.get('video')
        video_data = video_file.read()
        video_file.seek(0)
        
        # audio_analysis
        audio_file = request.files.get('audio')
        audio_file.save('dataset/audio_data3.wav') # binary data type
        audio, sr = process_audio('dataset/audio_data3.wav','./', 'new_audio3.wav')
        
        audio_info = {
            'sample_rate': sr,  # 샘플 레이트 등의 정보
            'audio_data': audio,  # 오디오 데이터
            'audio_path' : 'dataset/new_audio3.wav'
        }
        # 비동기로 처리하기 위해 executor.submit을 사용하여 함수를 실행
        future = executor3.submit(process_video_and_audio, video_data, audio_info, 3)       

        result = future.result()
        return jsonify({'result_emotion' : result})
    else:
        return 'No video or audio file found'
    
@app.route('/api/upload/4', methods=['POST'])
@cross_origin(origin='*localhost',headers=['Content- Type','Authorization','video/x-matroska;codecs=avc1', 'audio/ogg codecs=opus', 'audio/wav'])
def upload4():
    if 'video' in request.files and 'audio' in request.files:
        video_file = request.files.get('video')
        video_data = video_file.read()
        video_file.seek(0)
        
        # audio_analysis
        audio_file = request.files.get('audio')
        audio_file.save('dataset/audio_data4.wav') # binary data type
        audio, sr = process_audio('dataset/audio_data4.wav','./', 'new_audio4.wav')
        
        audio_info = {
            'sample_rate': sr,  # 샘플 레이트 등의 정보
            'audio_data': audio,  # 오디오 데이터
            'audio_path' : 'dataset/new_audio4.wav'
        }
        # 비동기로 처리하기 위해 executor.submit을 사용하여 함수를 실행
        future = executor4.submit(process_video_and_audio, video_data, audio_info, 4)       

        result = future.result()
        return jsonify({'result_emotion' : result})
    else:
        return 'No video or audio file found'

if __name__ == '__main__':
    app.run(debug=True, port=5001,host='0.0.0.0')
