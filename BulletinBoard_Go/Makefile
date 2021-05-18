CC = `which go`

SRC = $(shell (find . \( -name "*.go" ! -name "*_test.go" \)))
EXE = pubsub.out

all:
	$(CC) run $(SRC)

build:
	$(CC) build -o $(EXE) $(SRC)

vars:
	@echo COMPILER........$(CC)
	@echo SOURCE FILES...$(SRC)