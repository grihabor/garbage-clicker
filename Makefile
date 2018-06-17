include Makefile.version

all: next-micro

.PHONY: venv
venv:
	python3 -m virtualenv venv

.PHONY: install
install:
	pip install sphinx javasphinx sphinx_rtd_theme

.PHONY: travis-version
travis-version:
	@if [ ! -z "${TRAVIS_BRANCH}" ] && [ ${TRAVIS_BRANCH} = "master" ]; then \
	    $(MAKE) next-minor; \
	else \
	    $(MAKE) next-micro; \
	fi


