# BasicMVP
Simple implementation of MVP architecture for Android

`BasicMVP` allows you to create a presenter for every Activity or Fragment with very little boilerplate code. It manages the presenter along with the lifecycle of Activity or Fragment, so you can focus on coding the important stuff.

## Usage
You just need to extend `BasePresenter` for your presenter and `BasePresenterActivity` or `BasePresenterFragment` for your Activity or Fragment respectively.

```java
class SomePresenter extends BasePresenter<SomeActivity> {

}
```

```java
class SomeActivity extends BasePresenterActivity<SomePresenter> {

    @Override
    public SomePresenter createNewPresenter() {
        return new SomePresenter();
    }
}
```
OR
```java
class SomeFragment extends BasePresenterFragment<SomePresenter> {

    @Override
    public SomePresenter createNewPresenter() {
        return new SomePresenter();
    }
}
```

And you're done!