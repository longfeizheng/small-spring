package com.niocoder.test.v4;

import com.niocoder.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.niocoder.beans.factory.annotation.AutowiredFieldElement;
import com.niocoder.beans.factory.annotation.InjectionElement;
import com.niocoder.beans.factory.annotation.InjectionMetadata;
import com.niocoder.beans.factory.config.DependencyDescriptor;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.dao.v4.AccountDao;
import com.niocoder.dao.v4.ItemDao;
import com.niocoder.service.v4.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 *
 */
public class AutowiredAnnotationProcessorTest {

    AccountDao accountDao = new AccountDao();
    ItemDao itemDao = new ItemDao();

    DefaultBeanFactory beanFactory = new DefaultBeanFactory() {
        @Override
        public Object resolveDependency(DependencyDescriptor descriptor) {
            if (descriptor.getDependencyType().equals(AccountDao.class)) {
                return accountDao;
            }

            if (descriptor.getDependencyType().equals(ItemDao.class)) {
                return itemDao;
            }
            throw new RuntimeException("can't support types except AccountDao and ItemDao");
        }
    };

    @Test
    public void testGetInjectionMetadata() {
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setBeanFactory(beanFactory);
        InjectionMetadata injectionMetadata = processor.buildAutowiringMetadata(NioCoderService.class);
        List<InjectionElement> elements = injectionMetadata.getInjectionElements();
        Assert.assertEquals(2, elements.size());

        assertFieldExists(elements,"accountDao");
        assertFieldExists(elements,"itemDao");

        NioCoderService nioCoderService = new NioCoderService();
        injectionMetadata.inject(nioCoderService);

        Assert.assertTrue(nioCoderService.getAccountDao() instanceof AccountDao);

        Assert.assertTrue(nioCoderService.getItemDao() instanceof ItemDao);
    }

    private void assertFieldExists(List<InjectionElement> elements, String fieldName) {
        for (InjectionElement ele : elements) {
            AutowiredFieldElement fieldElement = (AutowiredFieldElement) ele;
            Field field = fieldElement.getField();
            if (field.getName().equals(fieldName)) {
                return;
            }
        }
        Assert.fail(fieldName + "does not exist!");
    }
}
