/**
 * Copyright 2012 StackMob
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

package com.stackmob.android.sdk.common;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

import com.stackmob.sdk.api.StackMobCookieStore;

public class StackMobAndroidCookieStore extends StackMobCookieStore {
	
	private SharedPreferences.Editor editor;
	
	public StackMobAndroidCookieStore(Context context) {
		super();
		SharedPreferences prefs = context.getSharedPreferences("stackmob." + StackMobCommon.API_KEY, 0);
		editor = prefs.edit();
		Map<String,?> cookies = prefs.getAll();
		for(String key : cookies.keySet()) {
			addToCookieMap((String)cookies.get(key));
		}
	}
	
	@Override
    protected void storeCookie(String cookieString) {
		super.storeCookie(cookieString);
		if(cookieString != null) {
			String key = cookieString.split("=")[0];
			editor.putString(key, cookieString);
			editor.apply();
		}
	}
	
	@Override
    public void clear() {
		super.clear();
		editor.clear();
		editor.apply();
	}
}
