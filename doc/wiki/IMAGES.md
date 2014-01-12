## DispenserView [(source)](https://github.com/alorma/capsulecorp/blob/master/Demo/src/main/java/cat/alorma/capsules/ui/fragment/ImagesCapsulesFragment.java)

![Screen](https://raw2.github.com/alorma/capsulecorp/master/doc/art/screen_images.png)

``` java
    Capsule capsule = new CustomUrlBitmapCapsule(getActivity(), "http://www.clker.com/cliparts/h/e/A/t/U/Z/red-star-hi.png");

    dispenserView.addCapsule(0, capsule);
```

ImageCapsule is an abstract class that allows to load images in Thread.

### Usage

* Create your class that extends from **ImageUrlCapsule**
* Implement Constructor matchig super(String url);
* Implement method executeAsync(String url);
* Use any async load images.
* Once bitmap is loaded, use **setDownloadedBitmap(bitmap);** to display in DispenserView

``` java
public class CustomUrlBitmapCapsule extends ImageUrlCapsule {

    public CustomUrlBitmapCapsule(Context ctx, String url) {
        super(url);
    }

    @Override
    protected void executeAsync(String url) {
        new AsyncBitmap().execute(url);
    }

    private class AsyncBitmap extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                java.net.URL url = new java.net.URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            setDownloadedBitmap(bitmap);
        }
    }
}
```

ImageUrlCapsule is not responsive of make it in a separated thread.