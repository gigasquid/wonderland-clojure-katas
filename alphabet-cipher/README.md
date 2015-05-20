# alphabet-cipher

Lewis Carroll published a cipher known as
[The Alphabet Cipher](http://en.wikipedia.org/wiki/The_Alphabet_Cipher)

![Letter](/images/fishfrogletter.gif)

This Alphabet Cipher involves alphabet substitution using a keyword.

First you must make a substitution chart like this, where each row of
the alphabet is rotated by one as each letter goes down the chart.

```
   ABCDEFGHIJKLMNOPQRSTUVWXYZ
 A abcdefghijklmnopqrstuvwxyz 
 B bcdefghijklmnopqrstuvwxyza 
 C cdefghijklmnopqrstuvwxyzab 
 D defghijklmnopqrstuvwxyzabc 
 E efghijklmnopqrstuvwxyzabcd 
 F fghijklmnopqrstuvwxyzabcde 
 G ghijklmnopqrstuvwxyzabcdef 
 H hijklmnopqrstuvwxyzabcdefg 
 I ijklmnopqrstuvwxyzabcdefgh 
 J jklmnopqrstuvwxyzabcdefghi 
 K klmnopqrstuvwxyzabcdefghij 
 L lmnopqrstuvwxyzabcdefghijk 
 M mnopqrstuvwxyzabcdefghijkl 
 N nopqrstuvwxyzabcdefghijklm 
 O opqrstuvwxyzabcdefghijklmn 
 P pqrstuvwxyzabcdefghijklmno 
 Q qrstuvwxyzabcdefghijklmnop 
 R rstuvwxyzabcdefghijklmnopq 
 S stuvwxyzabcdefghijklmnopqr 
 T tuvwxyzabcdefghijklmnopqrs 
 U uvwxyzabcdefghijklmnopqrst 
 V vwxyzabcdefghijklmnopqrstu 
 W wxyzabcdefghijklmnopqrstuv 
 X xyzabcdefghijklmnopqrstuvw 
 Y yzabcdefghijklmnopqrstuvwx 
 Z zabcdefghijklmnopqrstuvwxy 
```

Both parties need to decide on a secret keyword.  This keyword is not written down anywhere, but memorized.

To encode the message, first write down the message.

```
meetmebythetree
```

Then, write the keyword, (which in this case is _scones_), repeated as many times as necessary.

```
sconessconessco
meetmebythetree
```

Now you can look up the column _S_ in the table and follow it down until it meets the _M_ row. The value at the intersection is the letter _e_.  All the letters would be thus encoded.

```
sconessconessco
meetmebythetree
egsgqwtahuiljgs
```

The encoded message is now `egsgqwtahuiljgs`

To decode, the person would use the secret keyword and do the opposite.


## Instructions

- Clone or fork this repo
- `cd alphabet-cipher`
- Run the tests with `lein test`
- Make the tests pass!

## Solutions

Once you have your kata solution, you are welcome to submit a link to your repo to share here in this section with others.

* https://github.com/robhawkins/wonderland-clojure-katas/tree/master/alphabet-cipher
* https://github.com/gensym/wonderland-clojure-katas/tree/alphabet-cipher-solution/alphabet-cipher
* https://github.com/aaronj1335/wonderland-clojure-katas/tree/master/alphabet-cipher
* https://github.com/mwfogleman/wonderland-clojure-katas/tree/alphabet/alphabet-cipher
* https://github.com/vincentjames501/wonderland-clojure-katas/tree/master/alphabet-cipher
* https://github.com/rbxbx/wonderland-clojure-katas/tree/master/alphabet-cipher/
* https://github.com/gnandretta/wonderland-clojure-katas/tree/master/alphabet-cipher
* https://github.com/werand/wonderland-clojure-katas/tree/master/alphabet-cipher
* https://github.com/julianjelfs/wonderland-clojure-katas/tree/master/alphabet-cipher
* https://github.com/bartiosze/wonderland-clojure-katas/tree/origin-solution/alphabet-cipher
* https://github.com/armyofevilrobots/wonderland-clojure-katas/tree/aoer_run_mar2015/alphabet-cipher

If you haven't solved your kata yet - Don't Peek!

## License

Copyright © 2014 Carin Meier

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
