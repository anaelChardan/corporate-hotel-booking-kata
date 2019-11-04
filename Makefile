ROOT_DIR=$(shell pwd)
DOCKER_COMPOSE=docker-compose
GRADLE=$(DOCKER_COMPOSE) run --rm jvm gradle

.PHONY: clean
clean:
	$(GRADLE) clean

.PHONY: format
format:
	$(GRADLE) formatKotlin

.PHONY: build
build: clean format
	$(GRADLE) build

.PHONY: unit
unit: build
	$(GRADLE) test

.PHONY: acceptance
acceptance:
	$(GRADLE) clean build acceptance
