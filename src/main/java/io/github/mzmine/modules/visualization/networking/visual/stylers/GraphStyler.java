/*
 * Copyright (c) 2004-2022 The MZmine Development Team
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.mzmine.modules.visualization.networking.visual.stylers;

import com.google.common.collect.Range;
import io.github.mzmine.modules.visualization.networking.visual.enums.GraphElementAttr;
import io.github.mzmine.modules.visualization.networking.visual.enums.GraphObject;
import io.github.mzmine.modules.visualization.networking.visual.enums.GraphStyleAttribute;
import java.util.Map;
import java.util.function.Function;
import org.graphstream.graph.Graph;
import org.jetbrains.annotations.NotNull;

public sealed interface GraphStyler permits AbstractGraphStyler {

  GraphObject getGraphObject();

  void applyStyle(Graph graph, GraphElementAttr attribute,
      Function<GraphElementAttr, Range<Float>> valueRangeFunction,
      @NotNull Function<GraphElementAttr, Map<String, Integer>> valuesMapFunction);

  boolean matches(GraphObject go, GraphStyleAttribute gsa);

  GraphStyleAttribute getGraphStyleAttribute();


  /**
   * ratio (0-1) between min and maxIntensity
   *
   * @param value the intensity value
   * @return a value between 0-1 (including)
   */
  default float interpolate(float value, float min, float max) {
    return (float) Math.min(1.0, Math.max(0.0, (value - min) / (max - min)));
  }

  default float interpolate(final Range<Float> valueRange, final float value) {
    return interpolate(value, valueRange.lowerEndpoint(), valueRange.upperEndpoint());
  }

}