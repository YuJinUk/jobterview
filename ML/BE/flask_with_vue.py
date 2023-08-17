from flask import Flask, request, jsonify
from flask_cors import CORS, cross_origin
from concurrent.futures import ThreadPoolExecutor
from customLib.deepface_video import facial_expression_and_eye_movements
from customLib.voice_analysis import process_audio, audio_normalization, female_audio_normalization
from customLib.tfidf import wordcheck
import numpy as np
import os
from scipy.io import wavfile
import matplotlib.pyplot as plt
import speech_recognition

app = Flask(__name__)
cors = CORS(app, resources={r"/api/*": {"origins": "*"}})

executor1 = ThreadPoolExecutor(10)
executor2 = ThreadPoolExecutor(10)
executor3 = ThreadPoolExecutor(10)
executor4 = ThreadPoolExecutor(10)


def process_video_and_audio(video_data, audio_info, num, nickname, gender):
    
    with open(f'dataset/video_data_{nickname}_{num}.mp4', 'wb') as f:
        f.write(video_data)
    
    result_emotion, result_eye, eye_all, new_result_emotion_time = facial_expression_and_eye_movements(f'dataset/video_data_{nickname}_{num}.mp4')
    print(result_emotion)
    print(result_eye)
    result_emotion['eye_movement'] = result_eye
    result_emotion['all_eye'] = eye_all
    result_emotion['new_data_about_time'] = new_result_emotion_time
    
    # remove video in os folder
    video_path = f'dataset/video_data_{nickname}_{num}.mp4'
    if os.path.exists(video_path):
        os.remove(video_path)
        
    audio, sr, audio_path = audio_info['audio_data'], audio_info['sample_rate'], audio_info['audio_path']
    
    
    if gender == 'male':
        SER = audio_normalization(audio, sr, audio_path)
    elif gender == 'female':
        SER = female_audio_normalization(audio, sr, audio_path)
        
    result_emotion['SER'] = SER
    
    audio_STT = speech_recognition.Recognizer()
        
    audio_file = speech_recognition.AudioFile(audio_path)

    with audio_file as source:
        audio_text = audio_STT.record(source)
        
    text = audio_STT.recognize_google(audio_text, language='ko-KR', show_all=True)
    print(text)
    if text:
        result_emotion['STT_message'] = text['alternative'][0]['transcript']
        result_emotion['WordCheck'] = wordcheck(text['alternative'][0]['transcript'])
    else:
        result_emotion['STT_message'] = '아무 말도 하지 않음'
        
    if os.path.exists(audio_path):
        os.remove(audio_path)
        
    print(result_emotion)
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
        nickname = request.files.get('nickname').read()
        nickname = nickname.decode('utf-8')
        if 'female' in nickname:
            gender = 'female'
            nickname = nickname.replace('female', '')
        else:
            gender = 'male'
            nickname = nickname.replace('male', '')
            
        # audio_analysis
        audio_file = request.files.get('audio')
        audio_file.save(f'dataset/audio_data_{nickname}_1.wav') # binary data type
        audio, sr = process_audio(f'dataset/audio_data_{nickname}_1.wav', './', f'new_audio_{nickname}_1.wav')
        
        # remove audio in os folder
        audio_path = f'dataset/audio_data_{nickname}_1.wav'
        if os.path.exists(audio_path):
            os.remove(audio_path)
        audio_info = {
            'sample_rate': sr,  # 샘플 레이트 등의 정보
            'audio_data': audio,  # 오디오 데이터
            'audio_path' : f'dataset/new_audio_{nickname}_1.wav'
        }
        # 비동기로 처리하기 위해 executor.submit을 사용하여 함수를 실행
        future = executor1.submit(process_video_and_audio, video_data, audio_info, 1, nickname, gender)        

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
        nickname = request.files.get('nickname').read()
        nickname = nickname.decode('utf-8')
        if 'female' in nickname:
            gender = 'female'
            nickname = nickname.replace('female', '')
        else:
            gender = 'male'
            nickname = nickname.replace('male', '')
        # audio_analysis
        audio_file = request.files.get('audio')
        audio_file.save(f'dataset/audio_data_{nickname}_2.wav') # binary data type
        audio, sr = process_audio(f'dataset/audio_data_{nickname}_2.wav', './', f'new_audio_{nickname}_2.wav')
        
        audio_path = f'dataset/audio_data_{nickname}_2.wav'
        if os.path.exists(audio_path):
            os.remove(audio_path)
        
        audio_info = {
            'sample_rate': sr,  # 샘플 레이트 등의 정보
            'audio_data': audio,  # 오디오 데이터
            'audio_path' : f'dataset/new_audio_{nickname}_2.wav'
        }
        # 비동기로 처리하기 위해 executor.submit을 사용하여 함수를 실행
        future = executor2.submit(process_video_and_audio, video_data, audio_info, 2, nickname, gender)       

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
        
        nickname = request.files.get('nickname').read()
        nickname = nickname.decode('utf-8')
        if 'female' in nickname:
            gender = 'female'
            nickname = nickname.replace('female', '')
        else:
            gender = 'male'
            nickname = nickname.replace('male', '')
        
        # audio_analysis
        audio_file = request.files.get('audio')
        audio_file.save(f'dataset/audio_data_{nickname}_3.wav') # binary data type
        audio, sr = process_audio(f'dataset/audio_data_{nickname}_3.wav','./', f'new_audio_{nickname}_3.wav')
        
        audio_path = f'dataset/audio_data_{nickname}_3.wav'
        if os.path.exists(audio_path):
            os.remove(audio_path)
        
        audio_info = {
            'sample_rate': sr,  # 샘플 레이트 등의 정보
            'audio_data': audio,  # 오디오 데이터
            'audio_path' : f'dataset/new_audio_{nickname}_3.wav'
        }
        # 비동기로 처리하기 위해 executor.submit을 사용하여 함수를 실행
        future = executor3.submit(process_video_and_audio, video_data, audio_info, 3, nickname, gender)       

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
        
        nickname = request.files.get('nickname').read()
        nickname = nickname.decode('utf-8')
        if 'female' in nickname:
            gender = 'female'
            nickname = nickname.replace('female', '')
        else:
            gender = 'male'
            nickname = nickname.replace('male', '')
        # audio_analysis
        audio_file = request.files.get('audio')
        audio_file.save(f'dataset/audio_data_{nickname}_4.wav') # binary data type
        audio, sr = process_audio(f'dataset/audio_data_{nickname}_4.wav','./', f'new_audio_{nickname}_4.wav')
        
        audio_path = f'dataset/audio_data_{nickname}_4.wav'
        if os.path.exists(audio_path):
            os.remove(audio_path)
        
        audio_info = {
            'sample_rate': sr,  # 샘플 레이트 등의 정보
            'audio_data': audio,  # 오디오 데이터
            'audio_path' : f'dataset/new_audio_{nickname}_4.wav'
        }
        # 비동기로 처리하기 위해 executor.submit을 사용하여 함수를 실행
        future = executor4.submit(process_video_and_audio, video_data, audio_info, 4, nickname, gender)       

        result = future.result()
        return jsonify({'result_emotion' : result})
    else:
        return 'No video or audio file found'

if __name__ == '__main__':
    app.run(debug=True, port=5000, host='0.0.0.0')
