package de.jpwinkler.daf.documenttagging.doors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.eclipse.emf.common.util.BasicEList;
import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class DoorsDocumentAccessorTest {

    private DoorsModule node1;
    private DoorsObject node2;
    private DoorsObject node3;
    private DoorsObject node4;
    private DoorsDocumentAccessor accessor;

    @Before
    public void setup() {

        /**
         * We are going to setup the following tree:
         *
         * <code>
         *       node1
         *      /  |  \
         * node2 node3 node4
         * </code>
         *
         */

        node1 = mock(DoorsModule.class);
        node2 = mock(DoorsObject.class);
        node3 = mock(DoorsObject.class);
        node4 = mock(DoorsObject.class);

        when(node1.getParent()).thenReturn(null);
        when(node1.getObjects()).thenReturn(new BasicEList<>(Arrays.asList(node2, node3, node4)));
        when(node2.getParent()).thenReturn(node1);
        when(node3.getParent()).thenReturn(node1);
        when(node4.getParent()).thenReturn(node1);
        when(node2.getObjects()).thenReturn(new BasicEList<>());
        when(node3.getObjects()).thenReturn(new BasicEList<>());
        when(node4.getObjects()).thenReturn(new BasicEList<>());

        accessor = new DoorsDocumentAccessor(node1);
    }

    @Test
    public void testPrevious() {
        assertEquals(null, accessor.getPrevious(node1));
        assertEquals(null, accessor.getPrevious(node2));
        assertEquals(node2, accessor.getPrevious(node3));
        assertEquals(node3, accessor.getPrevious(node4));
    }

    @Test
    public void testNext() {
        assertEquals(null, accessor.getNext(node1));
        assertEquals(node3, accessor.getNext(node2));
        assertEquals(node4, accessor.getNext(node3));
        assertEquals(null, accessor.getNext(node4));
    }

    @Test
    public void testParent() {
        assertEquals(null, accessor.getParent(node1));
        assertEquals(node1, accessor.getParent(node2));
        assertEquals(node1, accessor.getParent(node3));
        assertEquals(node1, accessor.getParent(node4));
    }

    @Test
    public void testChildren() {
        assertThat(accessor.getChildren(node1)).containsExactly(node2, node3, node4);
        assertThat(accessor.getChildren(node2)).isEmpty();
        assertThat(accessor.getChildren(node3)).isEmpty();
        assertThat(accessor.getChildren(node4)).isEmpty();
    }

}
