# Python 이미지
FROM python:3.11

# Python 출력을 버퍼링하지 않도록 설정
ENV PYTHONUNBUFFERED 1

# 작업 디렉토리를 /code로 설정
WORKDIR /code

# requirements.txt 파일을 복사
COPY requirements.txt /code/

# pip를 업그레이드하고 필요한 패키지를 설치
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# 현재 디렉토리의 모든 파일을 컨테이너의 /code로 복사
COPY . /code/

# 서버 실행
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"] 
