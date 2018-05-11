VERSION := $(git describe --tags --always)
COMMIT  := $(shell git log --format=%h -1)
IMAGE   := grihabor/garbage.clicker
DATE    := $(shell date +'%Y-%m-%d_%H•%M•%S')

all: version
    
.PHONY: version
version:
    @echo "$(VERSION)"

.PHONY: image
image:
    @echo "$(IMAGE):$(VERSION)"
    