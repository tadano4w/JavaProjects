package java8test;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class StreamTest {

	@Test
	public void testSum() {
		Integer sum = Arrays.asList(1, 2, 3).stream().collect(Collectors.summingInt(v -> v));
		Assert.assertThat(sum, is(6));
	}

	@Test
	public void testOdds() {
		List<Integer> odss = Arrays.asList(1, 2, 3, 4, 5, 6).stream().filter(v -> v % 2 == 1).collect(Collectors.toList());
		Assert.assertThat(odss, is(Arrays.asList(1, 3, 5)));
	}

	@Test
	public void testAll() {
		boolean allOdds = Arrays.asList(1, 3, 5).stream().allMatch(v -> v % 2 == 1);
		Assert.assertThat(allOdds, is(true));

		boolean notAllOdds = Arrays.asList(1, 2, 3, 4, 5, 6).stream().allMatch(v -> v % 2 == 1);
		Assert.assertThat(notAllOdds, is(false));
}

	@Test
	public void testGrouping() {
		Map<Boolean, List<Integer>> result = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream().collect(Collectors.groupingBy(v -> v % 2 == 1));
		Assert.assertThat(result.get(true), is(Arrays.asList(1, 3, 5, 7, 9)));
		Assert.assertThat(result.get(false), is(Arrays.asList(2, 4, 6, 8)));
	}

	@Test
	public void testForEach() {
		List<Integer> list = new ArrayList<>();
		Arrays.asList(1, 2, 3).stream().forEach(v -> {
			list.add(2 * v);
		});
		Assert.assertThat(list, is(Arrays.asList(2, 4, 6)));
	}

	@Test
	public void testMap() {
		List<Integer> result = Arrays.asList(1, 2, 3).stream().map(v -> 2 * v).collect((Collectors.toList()));
		Assert.assertThat(result, is(Arrays.asList(2, 4, 6)));
	}

	@Test
	public void testFlatMap() {
		List<Integer> result =  Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)).stream().flatMap(v -> v.stream()).collect(Collectors.toList());
		Assert.assertThat(result, is(Arrays.asList(1, 2, 3, 4, 5, 6)));
	}

	@Test
	public void testReduce() {
		Integer sum = Arrays.asList(1, 2, 3, 4, 5).stream().reduce((a, b) -> a + b).orElse(-1);
		Assert.assertThat(sum, is(15));
	}

	@Test
	public void testLimit() {
		Assert.assertThat(Stream.iterate(1, n -> n + 1).limit(5).collect(Collectors.toList()), is(Arrays.asList(1, 2, 3, 4, 5)));
	}

	@Test
	public void testRange() {
		List<Integer> result1 = IntStream.range(1,  5).boxed().collect(Collectors.toList());
		Assert.assertThat(result1, is(Arrays.asList(1, 2, 3, 4)));

		List<Integer> result2 = IntStream.rangeClosed(1,  5).boxed().collect(Collectors.toList());
		Assert.assertThat(result2, is(Arrays.asList(1, 2, 3, 4, 5)));
	}
}
