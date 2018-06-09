MAKE              := make --no-print-directory

DESCRIBE          := $(shell git describe --match "v*" --always)
DESCRIBE_PARTS    := $(subst -, ,$(DESCRIBE))

VERSION_TAG       := $(word 1,$(DESCRIBE_PARTS))
COMMITS_SINCE_TAG := $(word 2,$(DESCRIBE_PARTS))

VERSION           := $(subst v,,$(VERSION_TAG))
VERSION_PARTS     := $(subst ., ,$(VERSION))

MAJOR             := $(word 1,$(VERSION_PARTS))
MINOR             := $(word 2,$(VERSION_PARTS))
MICRO             := $(word 3,$(VERSION_PARTS))

NEXT_MAJOR        := $(shell echo $$(($(MAJOR)+1)))
NEXT_MINOR        := $(shell echo $$(($(MINOR)+1)))

ifeq ($(strip $(COMMITS_SINCE_TAG)),)
NEXT_MICRO        := $(MICRO)
else 
NEXT_MICRO        := $(shell echo $$(($(MICRO)+$(COMMITS_SINCE_TAG))))
endif

VERSION_NEXT_MICRO := $(MAJOR).$(MINOR).$(NEXT_MICRO)
VERSION_NEXT_MINOR := $(MAJOR).$(NEXT_MINOR).0
VERSION_NEXT_MAJOR := $(NEXT_MAJOR).0.0

DATE                = $(shell date +'%d.%m.%Y')
TIME                = $(shell date +'%H:%M:%S')
COMMIT             := $(shell git rev-parse HEAD)
AUTHOR             := $(firstword $(subst @, ,$(shell git show --format="%aE" $(COMMIT))))

TAG_MESSAGE         = "$(TIME) $(DATE) $(AUTHOR)"


all: latest-version

.PHONY: latest-version
latest-version:
	@echo "v$(VERSION)"

.PHONY: next-micro
next-micro:
	@echo "v$(VERSION_NEXT_MICRO)"
	
.PHONY: next-minor
next-minor:
	@echo "v$(VERSION_NEXT_MINOR)"

.PHONY: next-major
next-major:
	@echo "v$(VERSION_NEXT_MAJOR)"

.PHONY: message
message:
	@echo "$(TAG_MESSAGE)"

.PHONY: travis-version
travis-version:
	@if [ ! -z "${TRAVIS_BRANCH}" ] && [ ${TRAVIS_BRANCH} = "master" ]; then \
	    $(MAKE) next-minor; \
	else \
	    $(MAKE) next-micro; \
	fi


