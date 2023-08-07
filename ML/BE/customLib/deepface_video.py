from deepface import DeepFace
import cv2
import numpy as np
import mediapipe as mp
import math
import datetime

mp_face_mesh = mp.solutions.face_mesh

LEFT_IRIS = [474, 475, 476, 477]
RIGHT_IRIS = [469, 470, 471, 472]

L_H_LEFT = [33]
L_H_RIGHT = [133]
R_H_LEFT = [362]
R_H_RIGHT = [263]

def facial_expression_and_eye_movements(video_file):
    cnt = 0
    print('eye tracking으로 들어음 file에 대하여 decode 해야함')
    now = datetime.datetime.now()
    data = {'angry': 0, 'disgust': 0, 'fear': 0, 'happy': 0,
            'sad': 0, 'surprise': 0, 'neutral': 0, 'time': []}
    eye_data = [0, 0, 0]
    video = cv2.VideoCapture(video_file)
    
    def euclidean_distance(point1, point2):
        x1, y1 =point1.ravel()
        x2, y2 =point2.ravel()
        distance = math.sqrt((x2-x1)**2 + (y2-y1)**2)
        return distance

    def iris_position(iris_center, right_point, left_point):
        center_to_right_dist = euclidean_distance(iris_center, right_point)
        total_distance = euclidean_distance(right_point, left_point)
        ratio = center_to_right_dist/total_distance
        iris_position =""
        if ratio <= 0.40 and ratio >= 0:
            iris_position="right"
        elif ratio > 0.40 and ratio <= 0.60:
            iris_position="center"
        elif ratio > 0.60 and ratio <= 1:
            iris_position = "left"
        return iris_position, ratio
    
    with mp_face_mesh.FaceMesh(max_num_faces=1, refine_landmarks=True, min_detection_confidence=0.5, min_tracking_confidence=0.5) as face_mesh:
        
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

            frame = cv2.flip(frame, 1)
            rgb_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)   #Mediapipe precisa do formato de cores RGB mas o OpenCV usa o BGR
            img_h, img_w = frame.shape[:2]
            results = face_mesh.process(rgb_frame)
            if results.multi_face_landmarks:
                mesh_points=np.array([np.multiply([p.x, p.y], [img_w, img_h]).astype(int) for p in results.multi_face_landmarks[0].landmark])

                (l_cx, l_cy), l_radius = cv2.minEnclosingCircle(mesh_points[LEFT_IRIS])
                (r_cx,r_cy), r_radius = cv2.minEnclosingCircle(mesh_points[RIGHT_IRIS])


                center_left = np.array([l_cx, l_cy], dtype=np.int32)
                center_right = np.array([r_cx, r_cy], dtype=np.int32)

                iris_pos, ratio = iris_position(center_left, mesh_points[R_H_RIGHT][0], mesh_points[R_H_LEFT][0])

                # print(iris_pos, ratio)
                if iris_pos == 'left':
                    eye_data[0] += 1
                elif iris_pos == 'right':
                    eye_data[2] += 1
                elif iris_pos == 'center':
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
    # del data['time']
    eye_data = str(100 - (100 * eye_data[1]/sum(eye_data))) + '%'
    return data, eye_data