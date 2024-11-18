install:
	make -C app ./gradlew clean install

run-dist:
	make -C app ./build/install/app/bin/app

check-updates:
	make -C app dependencyUpdates

lint:
	make -C app checkstyleMain

build:
	make -C app clean build

test:
	make -C app test

report:
	make -C app report
