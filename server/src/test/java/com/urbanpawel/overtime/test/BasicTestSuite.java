package com.urbanpawel.overtime.test;

import com.urbanpawel.overtime.DateTimeUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateTimeUtils.class)
public abstract class BasicTestSuite {
    @Before
    public void setUpPowerMockitoDateTimeUtilsMock() {
        PowerMockito.spy(DateTimeUtils.class);
        when(DateTimeUtils.now())
                .thenReturn(LocalDateTime.of(2018, Month.FEBRUARY, 14, 14, 0));
    }
}
