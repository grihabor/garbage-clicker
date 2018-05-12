MAKE          := make --no-print-directory

DESCRIBE      := $(shell git describe --match "*.*" --tags --always)
PARTS         := $(subst ., ,$(subst -, ,$(DESCRIBE)))

MAJOR         := $(word 1,$(PARTS))
MINOR         := $(word 2,$(PARTS))
MICRO         := $(word 3,$(PARTS))

NEXT_MAJOR    := $(shell echo $$(($(MAJOR)+1)))
NEXT_MINOR    := $(shell echo $$(($(MINOR)+1)))

VERSION       := $(MAJOR).$(MINOR).$(MICRO)-$(lastword $(PARTS))
COMMIT        := $(shell git log --format=%h -1)
IMAGE         := grihabor/garbage.clicker
DATE          := $(shell date +'%Y-%m-%d_%H•%M•%S')


all: version

.PHONY: version
version:
	@echo "$(VERSION)"

.PHONY: image
image:
	@echo "$(IMAGE):$(VERSION)"

.PHONY: next-minor
next-minor:
	@echo "$(MAJOR).$(NEXT_MINOR)"

.PHONY: next-major
next-major:
	@echo "$(NEXT_MAJOR).0"

.PHONY: travis-version
travis-version:
	@if [ ! -z "${TRAVIS_BRANCH}" ] && [ ${TRAVIS_BRANCH} = "master" ]; then \
	    $(MAKE) next-minor; \
	else \
	    $(MAKE) version; \
	fi
