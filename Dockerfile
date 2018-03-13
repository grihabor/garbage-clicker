FROM openjdk:8-jdk
MAINTAINER Borodin Gregory <grihabor@gmail.com>

ENV SDKMANAGER_URL https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip

RUN apt update \
 && apt install -y wget unzip

WORKDIR /sdk

# Download and extract sdk tools
RUN wget --quiet -O sdk-tools.zip $SDKMANAGER_URL \
 && unzip -q sdk-tools.zip \
 && rm sdk-tools.zip

WORKDIR /project

# Download and install gradle
ADD gradlew ./
ADD gradle ./gradle
RUN ./gradlew --no-daemon --version

ENV ANDROID_HOME /sdk
ENV PATH $PATH:/sdk/tools/bin

# Install dependencies
echo -e "travis_fold:start:android-sdk\033[33;1msdk\033[0m"
echo -e "\ntravis_fold:end:$1\r"
RUN yes | sdkmanager "build-tools;26.0.2"
RUN yes | sdkmanager "platforms;android-26"
RUN yes | sdkmanager --licenses

echo -e "\ntravis_fold:end:android-sdk\r"

ADD . .
RUN ./gradlew --no-daemon lint
RUN ./gradlew --no-daemon build
