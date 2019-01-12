package com.my.test.generate.plugins;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class PaginationAnnotaionPlugin extends PluginAdapter {

	
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
		
			/*FullyQualifiedJavaType superType = new FullyQualifiedJavaType(superClazz);
			topLevelClass.setSuperClass(superType);
			topLevelClass.addImportedType(superType);
			topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
			topLevelClass.addAnnotation("@Repository(\""+interfaze.getType().getShortName()+"\")");
			
			String pageClazz = getProperties().getProperty("pageClass");
			topLevelClass.addImportedType(new FullyQualifiedJavaType(pageClazz));
			Method m = new Method();
			m.setName("selectByPage");
			m.setVisibility(JavaVisibility.PUBLIC);
			m.addAnnotation("@SuppressWarnings(\"unchecked\")");
			m.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()) , "example"));
			m.addParameter(new Parameter(new FullyQualifiedJavaType(pageClazz) , "page"));
			FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
			returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
			m.setReturnType(returnType);
			
			String prifix = "";
//			System.out.println("schema============="+introspectedTable.getFullyQualifiedTable().getIntrospectedSchema());
			if(introspectedTable.getFullyQualifiedTable().getIntrospectedSchema() != null && introspectedTable.getFullyQualifiedTable().getIntrospectedSchema().length()>0){
				prifix = introspectedTable.getFullyQualifiedTable().getIntrospectedSchema()+"_";
			}
			m.addBodyLine(m.getReturnType().getShortName() + " list = this.searchListPageMyObject(\""+prifix+introspectedTable.getFullyQualifiedTable().getIntrospectedTableName()+".selectByExample\", example, page);");
			m.addBodyLine("return list;");
			topLevelClass.addMethod(m);*/
			
			
			String pageClazz = getProperties().getProperty("pageClass");
			interfaze.addImportedType(new FullyQualifiedJavaType(pageClazz));
			
			interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
			interfaze.addAnnotation("@Repository(\""+interfaze.getType().getShortName()+"\")");
			Method m1 = new Method();
			m1.setName("selectByPage");
			m1.setVisibility(JavaVisibility.PUBLIC);
			m1.addAnnotation("@SuppressWarnings(\"unchecked\")");
			m1.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()) , "example"));
			m1.addParameter(new Parameter(new FullyQualifiedJavaType(pageClazz) , "page"));
			FullyQualifiedJavaType interfaceReturnType = new FullyQualifiedJavaType(pageClazz);
			//interfaceReturnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
			m1.setReturnType(interfaceReturnType);
			interfaze.addMethod(m1);
			

		
		/*String template = getProperties().getProperty("sqlMapTemplate");
		if(template != null && template.trim().length()>0){
			FullyQualifiedJavaType superType = new FullyQualifiedJavaType("com.istock.base.ibatis.IbatisTemplate");
			topLevelClass.addImportedType(superType);
			FullyQualifiedJavaType resourceClass = new FullyQualifiedJavaType("javax.annotation.Resource");
			topLevelClass.addImportedType(resourceClass);

			Field templateField = new Field();
			templateField.addAnnotation("@Resource(name=\""+template+"\")");
			templateField.setName("template");
			templateField.setVisibility(JavaVisibility.PRIVATE);
			templateField.setType(new FullyQualifiedJavaType("com.istock.base.ibatis.IbatisTemplate"));
			topLevelClass.addField(templateField);
			
			Method getTemplate = new Method();
			getTemplate.setName("getSqlMapClientTemplate");
			getTemplate.setReturnType(new FullyQualifiedJavaType("com.istock.base.ibatis.IbatisTemplate"));
			getTemplate.addBodyLine("return this.template;");
			getTemplate.setVisibility(JavaVisibility.PUBLIC);
			topLevelClass.addMethod(getTemplate);
		}*/
		
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}
	
	
	






	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        String fqjt = introspectedTable.getExampleType();

        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$
        answer
                .addAttribute(new Attribute(
                        "id", "selectByPage")); //$NON-NLS-1$
        answer.addAttribute(new Attribute(
                "resultMap", introspectedTable.getBaseResultMapId())); //$NON-NLS-1$
        answer.addAttribute(new Attribute("parameterType", fqjt)); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);

        answer.addElement(new TextElement("select")); //$NON-NLS-1$
        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "distinct")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("distinct")); //$NON-NLS-1$
        answer.addElement(ifElement);

        StringBuilder sb = new StringBuilder();
        if (stringHasValue(introspectedTable
                .getSelectByExampleQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByExampleQueryId());
            sb.append("' as QUERYID,"); //$NON-NLS-1$
            answer.addElement(new TextElement(sb.toString()));
        }

        answer.addElement(getBaseColumnListElement( introspectedTable));
        answer.addElement(new TextElement(" ")); //$NON-NLS-1$
        //answer.addElement(getBlobColumnListElement(introspectedTable));

        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getExampleIncludeElement(introspectedTable));

        ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "orderByClause != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("order by ${orderByClause}")); //$NON-NLS-1$
        answer.addElement(ifElement);

        if (context.getPlugins()
                .sqlMapSelectByExampleWithBLOBsElementGenerated(answer,
                        introspectedTable)) {
            
            XmlElement parentElement = document.getRootElement();
            parentElement.addElement(answer);

        }
        return super.sqlMapDocumentGenerated(document, introspectedTable);


	}


	  protected XmlElement getBaseColumnListElement(IntrospectedTable introspectedTable) {
	        XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
	        answer.addAttribute(new Attribute("refid", //$NON-NLS-1$
	                introspectedTable.getBaseColumnListId()));
	        return answer;
	    }

	    protected XmlElement getBlobColumnListElement(IntrospectedTable introspectedTable) {
	        XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
	        answer.addAttribute(new Attribute("refid", //$NON-NLS-1$
	                introspectedTable.getBlobColumnListId()));
	        return answer;
	    }

	    protected XmlElement getExampleIncludeElement(IntrospectedTable introspectedTable) {
	        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
	        ifElement.addAttribute(new Attribute("test", "_parameter != null")); //$NON-NLS-1$ //$NON-NLS-2$

	        XmlElement includeElement = new XmlElement("include"); //$NON-NLS-1$
	        includeElement.addAttribute(new Attribute("refid", //$NON-NLS-1$
	                introspectedTable.getExampleWhereClauseId()));
	        ifElement.addElement(includeElement);

	        return ifElement;
	    }







	public boolean validate(List<String> warnings) {
		return true;
	}

}
