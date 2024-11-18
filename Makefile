install:
	make -C app install

run-dist:
	make -C app run-dist

check-updates:
	make -C app check-updates

lint:
	make -C app lint

build:
	make -C app build

test:
	make -C app test

report:
	make -C app report
