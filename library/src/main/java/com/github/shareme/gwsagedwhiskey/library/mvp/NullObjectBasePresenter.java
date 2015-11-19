/*
 *  Copyright 2015. Hannes Dorfmann.
 *  Modifications Copyright(C) 2015 Fred Grott(GrottWorkShop)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.shareme.gwsagedwhiskey.library.mvp;

import android.support.annotation.NonNull;

import com.github.shareme.gwsagedwhiskey.library.viewmodel.IView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A {@link Presenter} implmenetation that implements the <a href="https://en.wikipedia.org/wiki/Null_Object_pattern">null
 * object pattern</a> for the attached mvp view. So whenever the view gets detached from this
 * presenter by calling{@link #detachView(boolean)}, a new "null object" view gets dynamically
 * instantiated by using reflections and set as the current
 * view (instead of null). The new "null object" view simply does nothing. This avoids null pointer
 * exceptions and checks like {@code if (getView() != null)}
 *
 * @param <V> The type of the {@link IView}
 * @author Jens Dirller , Hannes Dorfmann
 * @see BasePresenter

 * Created by fgrott on 11/19/2015.
 */
@SuppressWarnings("unused")
public class NullObjectBasePresenter<V extends IView> implements Presenter<V> {

    private V view;

    @Override public void attachView(V view) {
        this.view = view;
    }

    @NonNull
    public V getView() {
        if (view == null) {
            throw new NullPointerException("IView reference is null. Have you called attachView()?");
        }
        return view;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void detachView(boolean retainInstance) {
        if (view != null) {

            Type[] types =
                    ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();

            Class<V> viewClass = (Class<V>) types[0];
            view = NoOp.of(viewClass);
        }
    }

}
