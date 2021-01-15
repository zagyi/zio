/*
 * Copyright 2019-2020 John A. De Goes and the ZIO Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zio.test

import zio.URLayer
import zio.duration._
import zio.test.environment.TestEnvironment
import zio.test.environment.Live

abstract class CustomRunnableSpec[R <: Live with Annotations](
  val customLayer: URLayer[TestEnvironment, R]
) extends RunnableSpec[R, Any] {

  override def aspects: List[TestAspect[Nothing, R, Nothing, Any]] =
    List(TestAspect.timeoutWarning(60.seconds))

  override def runner: TestRunner[R, Any] =
    customTestRunner

  override def environment: URLayer[TestEnvironment, R] =
    customLayer
}
