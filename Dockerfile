FROM openjdk:8-jdk
MAINTAINER Borodin Gregory <grihabor@gmail.com>

ENV SDKMANAGER_URL https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip

RUN apt update \
 && apt install -y wget unzip

WORKDIR /sdk

# Download and extract sdk tools
RUN wget -O sdk-tools.zip $SDKMANAGER_URL \
 && unzip sdk-tools.zip \
 && rm sdk-tools.zip

WORKDIR /project

# Download and install gradle
ADD gradlew ./
RUN chmod +x gradlew
ADD gradle ./gradle
RUN ./gradlew --no-daemon --version

ENV ANDROID_HOME /sdk
ENV PATH $PATH:/sdk/tools/bin

# Install dependencies
RUN sdkmanager --list
RUN yes | sdkmanager "build-tools;26.0.2"
RUN yes | sdkmanager "platforms;android-26"
RUN yes | sdkmanager --licenses
# RUN exit 1

ADD . .
RUN ./gradlew --no-daemon tasks
RUN ./gradlew --no-daemon build
