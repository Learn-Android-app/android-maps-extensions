/*
 * Copyright (C) 2013 Maciej Górski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.mg6.android.maps.extensions.demo;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidmapsextensions.ClusteringSettings;
import com.androidmapsextensions.GoogleMap.InfoWindowAdapter;
import com.androidmapsextensions.Marker;
import com.androidmapsextensions.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;

public class Issue15InfoWindowNotShowingClusterFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_map, container, false);
    }

    @Override
    protected void setUpMap() {
        ClusteringSettings settings = new ClusteringSettings();
        settings.clusterOptionsProvider(new DemoClusterOptionsProvider(getResources()));
        settings.addMarkersDynamically(true);
        map.setClustering(settings);

        map.setInfoWindowAdapter(new InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                TextView view = new TextView(getActivity());
                view.setText("info window");
                return view;
            }
        });

        MarkerOptions options = new MarkerOptions().position(new LatLng(50, 0)).title("title");
        map.addMarker(options);
        map.addMarker(options);

        new Handler().post(new Runnable() {

            @Override
            public void run() {
                map.getDisplayedMarkers().get(0).showInfoWindow();
            }
        });
    }
}
