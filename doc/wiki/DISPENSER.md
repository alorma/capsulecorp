## DispenserView [(source)](https://github.com/alorma/capsulecorp/blob/master/Demo/src/main/res/layout/fragment_main.xml)

![Screen](https://raw2.github.com/alorma/capsulecorp/master/doc/art/screen_colors.png)

``` xml
<cat.alorma.capsulecorp.library.DispenserView
        android:layout_width="300dp"
        android:layout_height="100dp"
        android:id="@+id/dispenserView"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true" />
```

DispenserView is a square view, it takes the big size  (Width or Height)

In activity or fragment findView:

``` java
DispenserView dispenserView = (DispenserView) view.findViewById(R.id.dispenserView);
```

Then you are able to add capsules.

``` java
Capsule capsule = new ColorCapsule(Color.parseColor("#e67e22"));
dispenserView.addCapsule(3, capsule);
```

DispenserView just admits postions from 0 to 3 (1 to 4).

### Modes

* **Single:** One capsule, square
* **Two columns:** Two capsules, one column per capsule
* **Column and squares:** Three capsules
    * Position 0 in a column
    * Position 1 in right top square
    * Position 2 in right bottom square
* **Squares:** Four capsules
    * Position 0 in left top square
    * Position 1 in right top square
    * Position 2 in left bottom square
    * Position 3 in right bottom square