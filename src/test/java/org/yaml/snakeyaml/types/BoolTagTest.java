/**
 * Copyright (c) 2008-2010 Andrey Somov
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
package org.yaml.snakeyaml.types;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @see http://yaml.org/type/int.html
 */
public class BoolTagTest extends AbstractTest {
    public void testBool() throws IOException {
        assertEquals(Boolean.TRUE, getMapValue("canonical: true", "canonical"));
        assertEquals(Boolean.FALSE, getMapValue("answer: NO", "answer"));
        assertEquals(Boolean.TRUE, getMapValue("logical: True", "logical"));
        assertEquals(Boolean.TRUE, getMapValue("option: on", "option"));
    }

    public void testBoolCanonical() throws IOException {
        assertEquals(Boolean.TRUE, getMapValue("canonical: Yes", "canonical"));
        assertEquals(Boolean.TRUE, getMapValue("canonical: yes", "canonical"));
        assertEquals(Boolean.TRUE, getMapValue("canonical: YES", "canonical"));
        assertEquals("yES", getMapValue("canonical: yES", "canonical"));
        assertEquals(Boolean.FALSE, getMapValue("canonical: No", "canonical"));
        assertEquals(Boolean.FALSE, getMapValue("canonical: NO", "canonical"));
        assertEquals(Boolean.FALSE, getMapValue("canonical: no", "canonical"));
        assertEquals(Boolean.FALSE, getMapValue("canonical: off", "canonical"));
        assertEquals(Boolean.FALSE, getMapValue("canonical: Off", "canonical"));
        assertEquals(Boolean.FALSE, getMapValue("canonical: OFF", "canonical"));
        assertEquals(Boolean.TRUE, getMapValue("canonical: ON", "canonical"));
        assertEquals(Boolean.TRUE, getMapValue("canonical: On", "canonical"));
        assertEquals(Boolean.TRUE, getMapValue("canonical: on", "canonical"));
        // it looks like it is against the specification but it is like in
        // PyYAML
        assertEquals("n", getMapValue("canonical: n", "canonical"));
        assertEquals("N", getMapValue("canonical: N", "canonical"));
        assertEquals("y", getMapValue("canonical: y", "canonical"));
        assertEquals("Y", getMapValue("canonical: Y", "canonical"));
    }

    public void testBoolShorthand() throws IOException {
        assertEquals(Boolean.TRUE, getMapValue("boolean: !!bool true", "boolean"));
    }

    public void testBoolTag() throws IOException {
        assertEquals(Boolean.TRUE,
                getMapValue("boolean: !<tag:yaml.org,2002:bool> true", "boolean"));
    }

    public void testBoolOut() throws IOException {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("boolean", Boolean.TRUE);
        String output = dump(map);
        assertTrue(output, output.contains("boolean: true"));
    }

}
