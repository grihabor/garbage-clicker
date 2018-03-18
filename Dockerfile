FROM openjdk:8-jdk
MAINTAINER Borodin Gregory <grihabor@gmail.com>

ENV SDKMANAGER_URL https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip

RUN echo -e "travis_fold:start:apt-install-deps\033[33;1mInstalling apt packages\033[0m" \
 && apt update \
 && apt install -y wget unzip \
 && echo -e "\ntravis_fold:end:apt-install-deps\r"

WORKDIR /sdk

# Download and extract sdk tools
RUN wget --quiet -O sdk-tools.zip $SDKMANAGER_URL \
 && unzip -q sdk-tools.zip \
 && rm sdk-tools.zip

WORKDIR /project

# Download and install gradle
ADD gradlew ./
ADD gradle ./gradle
RUN ./gradlew --quiet --no-daemon --version

ENV ANDROID_HOME /sdk
ENV PATH $PATH:/sdk/tools/bin

# Install android api and build tools
RUN echo -e "travis_fold:start:android-sdk\033[33;1mInstalling Android API and Build tools\033[0m" \
 && yes | sdkmanager "build-tools;26.0.2" \
 && yes | sdkmanager "platforms;android-26" \
 && yes | sdkmanager --licenses \
 && echo -e "\ntravis_fold:end:android-sdk\r"

# Add project files
ADD . .

# Execute lint task
RUN echo -e "travis_fold:start:lint-task\033[33;1mRunning lint task\033[0m" \
 && ./gradlew --no-daemon lint \
 && echo -e "\ntravis_fold:end:lint-task\r"

# Execute checkstyle task
RUN echo -e "travis_fold:start:check-task\033[33;1mRunning check task\033[0m" \
 && ./gradlew --no-daemon checkstyleAndroid \
 && echo -e "\ntravis_fold:end:check-task\r"

# Execute build task
RUN echo -e "travis_fold:start:build-task\033[33;1mRunning build task\033[0m" \
 && ./gradlew --no-daemon build \
 && echo -e "\ntravis_fold:end:build-task\r"

