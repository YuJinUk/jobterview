from deepface import DeepFace
import cv2
import numpy as np
import mediapipe as mp
import math
import datetime

FER = mp.solutions.face_mesh

left_eye = [474, 475, 476, 477]
right_eye = [469, 470, 471, 472]

left_num = [362]
right_num = [263]

def facial_expression_and_eye_movements(video_file):
    cnt = 0
    now = datetime.datetime.now()
    data = {'angry': 0, 'disgust': 0, 'fear': 0, 'happy': 0,
            'sad': 0, 'surprise': 0, 'neutral': 0, 'time': []}
    eye_data = [0, 0, 0]
    data_new = {'angry': [], 'disgust': [], 'fear': [], 'happy': [],
                'sad': [], 'surprise': [], 'neutral': [], 'time': []}
    video = cv2.VideoCapture(video_file)
    
    def euclidean_distance(first, second):
        x1, y1 =first.ravel()
        x2, y2 =second.ravel()
        distance = math.sqrt((x2-x1)**2 + (y2-y1)**2)
        return distance

    def eye_position(middel, right, left):
        dist = euclidean_distance(middel, right) # 동공의 중앙과 오른쪽 거리 계산
        lefttoright = euclidean_distance(right, left) # 눈의 좌, 우측 거리 계산
        ratio = dist/lefttoright # 거리의 비율 계산
        where =""
        if ratio <= 0.48 and ratio >= 0:
            where="right"
        elif ratio > 0.48 and ratio <= 0.52:
            where="center"
        elif ratio > 0.52 and ratio <= 1:
            where = "left"
        return where, ratio
    
    with FER.FaceMesh(max_num_faces=1, refine_landmarks=True, min_detection_confidence=0.5, min_tracking_confidence=0.5) as face_mesh:
        
        while True:
            status, frame = video.read()
            cnt += 1
            if not status:
                break
            if cnt%5:
                continue
            face_analysis = DeepFace.analyze(frame, actions=['emotion'], enforce_detection=False)
        
            data_f = face_analysis[0]['emotion']
            check_main_emotion = face_analysis[0]['dominant_emotion']
            main_emotion = 'neutral'
            
            # if main_emotion != check_main_emotion:
            #     data['time'].append((datetime.datetime.now() - now).total_seconds())
            
            for ki in data_f:
                data[ki] += data_f[ki]
                data_new[ki].append(round(data_f[ki], 2))
            
            data_new['time'].append(cnt)

            frame = cv2.flip(frame, 1)
            new_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)   #Mediapipe precisa do formato de cores RGB mas o OpenCV usa o BGR
            heights_img, width_img = frame.shape[:2]
            results = face_mesh.process(new_frame)
            if results.multi_face_landmarks:
                mesh_points=np.array([np.multiply([i.x, i.y], [width_img, heights_img]).astype(int) for i in results.multi_face_landmarks[0].landmark])

                (check_x, check_y), left_radius = cv2.minEnclosingCircle(mesh_points[left_eye])
                (new_check_x,new_check_y), right_radius = cv2.minEnclosingCircle(mesh_points[right_eye])


                centerleft = np.array([check_x, check_y], dtype=np.int32)
                center_right = np.array([new_check_x, new_check_y], dtype=np.int32)

                result_position, ratio = eye_position(centerleft, mesh_points[right_num][0], mesh_points[left_num][0])

                # print(result_position, ratio)
                if result_position == 'left':
                    eye_data[0] += 1
                elif result_position == 'right':
                    eye_data[2] += 1
                elif result_position == 'center':
                    eye_data[1] += 1
    
    main_emotion = ''
    main_emotion_value = 0
    all_value = 0
    for i in data:
        if i != 'time':
            all_value += data[i]
            if data[i] > main_emotion_value:
                main_emotion_value = data[i]
                main_emotion = i
    for i in data:
        if i != 'time':
            data[i] = str(round(100 * data[i]/all_value, 3)) + '%'
    
    data['main_emotion'] = [main_emotion, str(round(100 * main_emotion_value/all_value, 3)) + '%']
    
    for idx, checkpoint in enumerate(data_new['time']):
        data_new['time'][idx] = 60 * checkpoint // cnt
    
    
    # del data['time']
    center_eye_data = 100 - (100 * eye_data[1]/sum(eye_data))
    return data, center_eye_data, eye_data, data_new
