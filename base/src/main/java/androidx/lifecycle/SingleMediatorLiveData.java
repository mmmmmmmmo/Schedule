package androidx.lifecycle;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 单数据源监听LiveData
 */
public class SingleMediatorLiveData<T> extends MutableLiveData<T> {

    private SingleMediatorLiveData.Source<?> mSource;

    @MainThread
    public <S> void addSource(@NonNull LiveData<S> source, @NonNull Observer<? super S> onChanged) {
        SingleMediatorLiveData.Source<S> e = new SingleMediatorLiveData.Source<>(source, onChanged);
        removeSource();
        mSource = e;
        if (hasActiveObservers()) {
            e.plug();
        }
    }

    @MainThread
    public void removeSource() {
        if (mSource != null) {
            mSource.unplug();
        }
    }

    @CallSuper
    @Override
    protected void onActive() {
        if (mSource != null) {
            mSource.plug();
        }
    }

    @CallSuper
    @Override
    protected void onInactive() {
        if (mSource != null) {
            mSource.unplug();
        }
    }

    private static class Source<V> implements Observer<V> {
        final LiveData<V> mLiveData;
        final Observer<? super V> mObserver;
        int mVersion = -1;

        Source(LiveData<V> liveData, final Observer<? super V> observer) {
            mLiveData = liveData;
            mObserver = observer;
        }

        void plug() {
            mLiveData.observeForever(this);
        }

        void unplug() {
            mLiveData.removeObserver(this);
        }

        @Override
        public void onChanged(@Nullable V v) {
            if (mVersion != mLiveData.getVersion()) {
                mVersion = mLiveData.getVersion();
                mObserver.onChanged(v);
            }
        }
    }
}
