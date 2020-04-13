package com.firstspring.testspring;

import com.firstspring.testspring.dao.TvSeriesDao;
import com.firstspring.testspring.data.TvSeries;
import com.firstspring.testspring.services.TvSeriesSevice;
import com.firstspring.testspring.data.tvCharacterDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;






/**
 * 此测试用例是测试业务逻辑层TvSeriesService的例子，业务逻辑层下的数据访问层通过MockBean加载的桩模块实现。
 * 避免了测试过程中对数据库内数据的依赖，造成很难写成功失败标准的状况
 *
 */
@SpringBootTest
class TestspringApplicationTests {
	/**
	 * 实现一个单元测试用的桩模块
	 * @MockBean可给当前的spring context装载一个假的bean上去替代原有的同名bean；
	 * mock了dao的所有bean后，数据访问层就别接管了，从而实现测试不受具体数据库内数据值影响的结果
	 */
	@MockBean
	TvSeriesDao tvSeriesDao;
	@MockBean
	tvCharacterDto tvCharacterDao;

	@Autowired
	TvSeriesSevice tvSeriesService;

	@Test
	public void testGetAllWithoutMockit() {
		List<TvSeries> list = tvSeriesService.getAllTvSeries();
		//这里的测试结果依赖连接数据库内的记录，很难写一个判断是否成功的条件，甚至无法执行
		//下面的testGetAll()方法，使用了mock出来的dao作为桩模块，避免了这一情形
		Assert.assertTrue(list.size() >= 0);
	}

	@Test
	public void testGetAll() {
		//设置一个TvSeries List
		List<TvSeries> list = new ArrayList<>();
		TvSeries ts = new TvSeries();
		ts.setName("西部世界");
		list.add(ts);

		//下面这个语句是告诉mock出来tvSeriesDao当执行getAll方法时，返回上面创建的那个list
		Mockito.when(tvSeriesDao.getAll()).thenReturn(list);

		//测试tvSeriesService的getAllTvSeries()方法，获得返回值
		List<TvSeries> result = tvSeriesService.getAllTvSeries();

		//判断返回值是否和最初做的那个list相同，应该是相同的。
		Assert.assertTrue(result.size() == list.size());
		Assert.assertTrue("POI".equals(result.get(0).getName()));
	}



	@Test
	void contextLoads() {

	}

}
