package main

import "testing"

func TestDataStoreLoad(t *testing.T) {

	d := DataStorage{Filepath: "resources/words.txt"}
	d.load()

	if len(d.Words) == 0 {
		t.FailNow()
	}

	for _, word := range d.Words {
		t.Logf("%s", word)
	}
}
