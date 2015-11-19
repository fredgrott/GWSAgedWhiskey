package com.github.shareme.gwsagedwhiskey.library.mvp;

import com.github.shareme.gwsagedwhiskey.library.viewmodel.IView;

/**
 * Presenter interface
 * Created by fgrott on 11/19/2015.
 */
@SuppressWarnings("unused")
public interface Presenter<V extends IView> {

    /**
     * Set or attach the view to this presenter
     */
    void attachView(V view);

    /**
     * Will be called if the view has been destroyed. Typically this method will be invoked from
     * <code>Activity.detachView()</code> or <code>Fragment.onDestroyView()</code>
     */
    void detachView(boolean retainInstance);

}
