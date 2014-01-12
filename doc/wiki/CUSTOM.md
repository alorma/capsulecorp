## Custom capsule

* Create your class that extends from **Capsule**

``` java
public class AwesomeCapsule extends Capsule {
...
}
```

* Implement method **create(Canvas canvas, Paint paint, Rect rect);**

``` java
@Override
public void create(Canvas canvas, Paint paint, Rect rect) {
 {...}
}
```

Inside this method you can print anything you want:

``` java
paint.setColor(this.background);
paint.setStyle(Paint.Style.FILL);
canvas.drawRect(rect, paint);
```
