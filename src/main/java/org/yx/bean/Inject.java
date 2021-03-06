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
package org.yx.bean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 相当于spring的AutoWired，使用在全局变量上.它只会注入当前值为null的字段。<br>
 * 下列三种方式优先级从高到低注入对象，只有在上一种方式无法获取对象的时候，才使用下一种方式注入：<br>
 * 1、使用name和clz获取对象<br>
 * 2、使用name获取对象<br>
 * 3、使用clz获取对象<br>
 * 
 * @author 游夏
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inject {

	/**
	 * bean类的实际类型，默认是变量定义时候的类型<BR>
	 * 它的用处是多个bean具有相同的name， 这时候如果字段的类型不想定义为实际的类，可以用这个注解来指定实际的类
	 */
	Class<?> beanClz() default Object.class;
}
