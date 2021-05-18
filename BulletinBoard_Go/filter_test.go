package main

import (
	"reflect"
	"testing"
)

type FilterTests struct {
	Word       string
	IsStopWord bool
}

func TestFilterLoad(t *testing.T) {

	f := Filter{Filepath: "resources/stop_words.txt"}
	f.load()

	if len(f.Marked) == 0 {
		t.FailNow()
	}
}

func TestFilter(t *testing.T) {

	f := Filter{Filepath: "resources/stop_words.txt"}
	f.load()

	tests := []FilterTests{
		{Word: "something", IsStopWord: false},
		{Word: "distinguished", IsStopWord: false},
		{Word: "under", IsStopWord: true},
		{Word: "down", IsStopWord: true},
		{Word: "in", IsStopWord: true},
		{Word: "how", IsStopWord: true},
		{Word: "few", IsStopWord: true},
		{Word: "something", IsStopWord: false},
		{Word: "should", IsStopWord: true},
		{Word: "something", IsStopWord: false},
		{Word: "something", IsStopWord: false},
		{Word: "very", IsStopWord: true},
	}

	for _, test := range tests {
		assertion := f.isStopWord(test.Word)

		if !reflect.DeepEqual(assertion, test.IsStopWord) {
			t.Logf("%s wants %v, got %v", test.Word, test.IsStopWord,
				assertion)
		}
	}

}