/**
 * Copyright (C) 2016 - 2017 youtongluan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yx.redis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.yx.log.Log;

public class RedisPool {
	private static final Map<String, Redis> map = new ConcurrentHashMap<>();

	private static final Map<String, RedisParamter[]> readParamsMap = new ConcurrentHashMap<>();

	static Redis _defaultRedis;
	static {
		try {
			RedisLoader.init();
		} catch (Exception e) {
			Log.printStack(e);
			System.exit(-1);
		}
	}

	/**
	 * 获取已经存在的redis，获取不到就返回默认的，如果连默认的都没有，就返回null
	 * 
	 * @param alias
	 * @return
	 */
	public static Redis get(String alias) {
		if (alias == null) {
			return _defaultRedis;
		}
		alias = alias.toLowerCase();
		Redis r = map.get(alias);
		if (r != null) {
			return r;
		}
		return _defaultRedis;
	}

	public static Redis getRedisExactly(String alias) {
		alias = alias.toLowerCase();
		return map.get(alias);
	}

	/**
	 * 默认的redis
	 * 
	 * @return
	 */
	public static Redis defaultRedis() {
		return _defaultRedis;
	}

	public static void put(String alias, Redis redis) {
		map.putIfAbsent(alias.toLowerCase(), redis);
	}

	static void attachRead(String host, RedisParamter[] reads) {
		readParamsMap.put(host, reads);
	}
}
