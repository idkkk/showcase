package org.rubik.sandbox.guava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class CacheTest {

    private Cache<String, String> cache;

    @Before
    public void setUp() throws Exception {
        cache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES).maximumSize(10000).build();
    }

    @After
    public void tearDown() throws Exception {
        cache.invalidateAll();
    }

    @Test
    public void testCacheBuilder() {
        String key = "";
        for (int i = 0; i < 10; i++) {
            key = Strings.padStart(String.valueOf(i), 5, '0');
            cache.put(key, key);
        }
        
        assertEquals("00001", cache.getIfPresent("00001"));
        
        cache.invalidate("00001");
        assertNull(cache.getIfPresent("00001"));
    }

    @Test
    public void testBloomFilter() {
        BloomFilter<Long> bloomFilter = BloomFilter.create(Funnels.longFunnel(), 100000, 0.01);
        for (int i = 0; i < 100000; i++) {
            bloomFilter.put(Math.round(Math.random() * 100000));
        }

        boolean mayBeContained = bloomFilter.mightContain(20568l);
        assertTrue(mayBeContained);
    }

}
