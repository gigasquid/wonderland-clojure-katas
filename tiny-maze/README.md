# tiny-maze

Alice found herself very tiny and wandering around Wonderland.  Even
the grass around her seemed like a maze.

![alice tiny](/images/alicetiny.gif)

This is a tiny maze solver.

A maze is represented by a matrix

```clojure
[[:S 0 1]
 [1  0 1]
 [1  0 :E]]
```

- _S_ : start of the maze
- _E_ : end of the maze
- _1_ : This is a wall that you cannot pass through
- _0_ : A free space that you can move through.

The goal is the get to the end of the maze.  A solved maze will have a
_:x_ in the start, the path, and the end of the maze, like this.

```clojure
[[:x :x 1]
 [1  :x 1]
 [1  :x :x]]
```


## Instructions

- Clone or fork this repo
- `cd tiny-maze`
- Run the tests with `lein test`
- Make the tests pass!

## Solutions

Once you have your kata solution, you are welcome to submit a link to your repo to share here in this section with others.

* https://github.com/vincentjames501/wonderland-clojure-katas/tree/master/tiny-maze
* https://github.com/julianjelfs/wonderland-clojure-katas/tree/master/tiny-maze
* https://github.com/werand/wonderland-clojure-katas/tree/master/tiny-maze
* https://github.com/priort/wonderland-clojure-katas/tree/master/tiny-maze
* https://github.com/bradlucas/wonderland-clojure-katas/tree/tiny-maze/tiny-maze
* https://github.com/davidpham87/wonderland-clojure-katas/tree/my-training/tiny-maze
* https://github.com/ultrakapy/wonderland-clojure-katas/tree/master/tiny-maze
* https://github.com/emmagordon/wonderland-clojure-katas/tree/master/tiny-maze
* https://github.com/ivern/wonderland-clojure-katas/tree/master/tiny-maze
* https://github.com/RokLenarcic/wonderland-clojure-katas/tree/master/tiny-maze
* https://github.com/kimsnj/wonderland-clojure-katas/tree/master/tiny-maze
* https://github.com/whiteotter/wonderland-clojure-katas/tree/master/tiny-maze

If you haven't solved your kata yet - Don't Peek!

## License

Copyright © 2014 Carin Meier

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
