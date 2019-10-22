ROOT_DIR=$(shell pwd)
DOCKER_COMPOSE=docker-compose
GRADLE=$(DOCKER_COMPOSE) run --rm jvm gradle

.PHONY: format
format:
	$(GRADLE) formatKotlin

.PHONY: build
build: format
	$(GRADLE) build

.PHONY: test
test: build
	$(GRADLE) test