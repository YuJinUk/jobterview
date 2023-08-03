process=$(sudo lsof -i :8060 | awk 'NR==2{print $2}')

if [[ -n "$process" ]]; then
    echo "포트 8060을 사용하는 프로세스($process)를 종료합니다."
    sudo kill "$process"
else
    echo "포트 8060을 사용하는 프로세스가 없습니다."
fi