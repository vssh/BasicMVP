# BasicMVP
Simple implementation of MVP architecture for Android

`BasicMVP` allows you to create a presenter for every Activity or Fragment with very little boilerplate code. It manages the presenter along with the lifecycle of Activity or Fragment, so you can focus on coding the important stuff.

## Usage
First, create interfaces for your view and presenter by extending `BmvpViewInterface` and `BmpvPresenterInterface` respectively.

```java
interface SomeViewInterface extends BmvpViewInterface {

}
```
```java
interface SomePresenterInterface extends BmvpPresenterInterface<SomeViewInterface> {

}
```

Then, extend `BmvpPresenter` for your presenter and `BmvpActivity` or `BmvpFragment` for your Activity or Fragment respectively.

```java
class SomePresenter extends BmvpPresenter<SomeViewInterface> implements SomePresenterInterface {

}
```

```java
class SomeActivity extends BmvpActivity<SomePresenterInterface> implements SomeViewInterface {

    @Override
    public SomePresenter createNewPresenter() {
        return new SomePresenter();
    }
}
```
OR
```java
class SomeFragment extends BmvpFragment<SomePresenterInterface> implements SomeViewInterface {

    @Override
    public SomePresenter createNewPresenter() {
        return new SomePresenter();
    }
}
```

And you're done!
