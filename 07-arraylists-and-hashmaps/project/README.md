# Module 7 Project: Vocabulary Quiz Master

Build a quiz program that helps someone learn **vocabulary**: words in
another language, terms from a subject you're studying (biology,
electronics, law…), or anything else that comes in "word → meaning"
pairs. It quizzes in a random order, remembers the words you get wrong,
and lets you practise just those.

```
=== VOCABULARY QUIZ MASTER: 10 words ===
1) Take the quiz
2) Practise the words I got wrong
3) Add a word
4) Show all words
5) Quit
Choose: 1
What does "nsuo" mean? water
Correct!
What does "medaase" mean? welcome
Not quite: "medaase" means "thank you".
...
You scored 8/10 (80%).
```

The starter has a few words of Twi. Swap them for whatever you actually
want to learn.

## Requirements

Your program must:

1. Store the vocabulary in a `HashMap<String, String>` (word → meaning),
   with **at least ten** words to start with.
2. **Take the quiz:** ask about every word once, in a **random order**
   (copy the keys into an `ArrayList` and use `Collections.shuffle`).
   Accept answers in any capitals, with extra spaces trimmed. Print the
   score and a percentage at the end.
3. **Remember mistakes:** keep an `ArrayList<String>` of words answered
   wrongly, with **no duplicates**, even if a word is missed in several
   quizzes.
4. **Practise mistakes:** quiz only the words on the "wrong" list, and
   remove each one as soon as it's answered correctly.
5. **Add a word:** if the word already exists, show its current meaning
   and ask whether to replace it.
6. **Show all words** in alphabetical order (a `TreeMap` sorts them for
   you), lined up in columns with `printf`.
7. Use a `do`-`while` menu, and handle invalid choices.

**Stretch goals** (pick any):

- Let the player choose the direction: show the word and ask for the
  meaning, or show the meaning and ask for the word. (Hint: build a
  second map with the keys and values swapped.)
- Accept more than one correct meaning: store meanings like
  `"thank you/thanks"`, `split("/")` them, and accept any one.
- Keep a `HashMap<String, Integer>` of how many times each word has been
  missed, and show the three hardest words.

## Ideas if you're stuck

- Get **one** menu option working at a time, starting with option 4
  (it's the easiest, and it lets you check the map is right).
- Comparing the answer: `answer.trim().equalsIgnoreCase(vocab.get(word))`.
- "No duplicates" in the wrong list: check `wrong.contains(word)` before
  adding. (Or think about whether a different collection from this
  module would make duplicates impossible.)
- If the "practise" option crashes with a
  `ConcurrentModificationException`, you're removing from the list while
  looping over it with for-each. Loop over a **copy** of the list
  (`new ArrayList<>(wrong)`) and remove from the original.

## Getting started

Copy [`VocabQuiz.java`](VocabQuiz.java) into your coursework folder and
follow the TODOs.

```bash
java VocabQuiz.java
```

## Done?

```bash
git add .
git commit -m "Complete Module 7 project: vocabulary quiz"
git push
```
