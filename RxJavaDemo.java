// Example 1: Basic Observable and Observer
Observable<String> observable1 = Observable.just("Hello RxJava");
observable1.subscribe(System.out::println); // prints: Hello RxJava

// Example 2: Explicit Observer
Observable<String> observable2 = Observable.just("Apple", "Banana");
Observer<String> observer = new Observer<String>() {
    @Override public void onSubscribe(Disposable d) {}
    @Override public void onNext(String item) { System.out.println(item); }
    @Override public void onError(Throwable e) { e.printStackTrace(); }
    @Override public void onComplete() { System.out.println("Done"); }
};
observable2.subscribe(observer);

// Example 3: Operators (map)
Observable.just("apple")
    .map(String::toUpperCase)
    .subscribe(System.out::println); // prints: APPLE

// Example 4: Chaining Operators
Observable.just("apple", "banana", "cherry")
    .filter(fruit -> fruit.startsWith("b"))
    .map(String::toUpperCase)
    .subscribe(System.out::println); // prints: BANANA

// Example 5: Threading with Schedulers
Observable.just("Threaded Data")
    .subscribeOn(Schedulers.io()) // run on IO thread
    .observeOn(AndroidSchedulers.mainThread()) // observe on main UI thread
    .subscribe(System.out::println);

// Example 6: Error Handling with onErrorReturnItem
Observable<String> errorObservable = Observable.create(emitter -> {
    emitter.onError(new RuntimeException("Oops!"));
});
errorObservable
    .onErrorReturnItem("default value")
    .subscribe(System.out::println); // prints: default value

// Example 7: CompositeDisposable for managing lifecycle
CompositeDisposable disposables = new CompositeDisposable();
disposables.add(
    Observable.just("Clean Up")
        .subscribe(System.out::println)
);
// Call disposables.clear() in onDestroy() to prevent memory leaks

// Example 8: Retrofit Integration (interface only)
public interface ApiService {
    @GET("users")
    Observable<List<User>> getUsers();
}

// Example 9: Room DAO returning Flowable
@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    Flowable<List<User>> getAllUsers();
}


/* ✅ 1. RxJava Core
groovy
Copy
Edit
implementation 'io.reactivex.rxjava3:rxjava:3.1.8'
If you're using RxJava2 (which is still common in some Android libraries):

groovy
Copy
Edit
implementation 'io.reactivex.rxjava2:rxjava:2.2.21'
✅ 2. RxAndroid (for main thread scheduling)
groovy
Copy
Edit
implementation 'io.reactivex.rxjava3:rxandroid:3.0.2'
For RxJava2:

groovy
Copy
Edit
implementation 'io.reactivex.rxjava2:rxandroid:2.1.1' */
