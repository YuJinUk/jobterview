container_name="flask"

if docker ps -a | grep -q "$container_name" ; then
    # 컨테이너가 존재하는 경우 정지
    docker stop "$container_name"
    docker rm "$container_name"
    echo "컨테이너를 멈춘 후 제거했습니다."
else
# 컨테이너가 존재하지 않는 경우 메시지 출력
    echo "컨테이너가 존재하지 않습니다."
fi