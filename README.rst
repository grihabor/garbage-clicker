Garbage Clicker
===============

|build|

Garbage Clicker = Переработка мусора + Cookie Clicker

`Github Pages`_ 

.. _`Github Pages`: https://grihabor.github.io/garbage-clicker

.. |build| image:: https://travis-ci.org/grihabor/garbage-clicker.svg?branch=master
    :target: https://travis-ci.org/grihabor/garbage-clicker

.. |docker-image| image:: https://img.shields.io/docker/pulls/grihabor/garbage.clicker.svg
    :target: https://hub.docker.com/r/grihabor/garbage.clicker
    
Development
-----------

1. Download [android cli tools](https://developer.android.com/tools/sdkmanager).
2. Put them in `~/.android/cmdline-tools/`.
3. Set env vars:

```bash
export PATH="$PATH:$HOME/.android/cmdline-tools/latest/bin/"
export ANDROID_HOME=$HOME/.android/
```

4. Inspect keystore:

```bash
fd --no-ignore keystore | xargs keytool -v -list -keystore
```

5. Set secret env vars in `.env`:

```bash
export KEY_ALIAS=
export KEY_PASSWORD=
export KEYSTORE_PASSWORD=
```

6. Then build:

```bash
. .env
./gradlew assemble
```

