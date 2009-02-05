<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" 
    xmlns:xlink="http://www.w3.org/1999/xlink#"
    xmlns:ifc="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs#"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance#"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema#"   
    xmlns:ex="urn:iso10303-28:ex"   
    >
    
    <!-- resourceURL must be = ifcLoadedFile HOW? given as parameter -->
    
    <xsl:variable name="resourceURL">
        <xsl:text>E:/fev2008/IFCdata/testIfc1.xml</xsl:text>
    </xsl:variable>
    

    <xsl:variable name="refLocal">
        <xsl:value-of select="$resourceURL"/><xsl:text>#</xsl:text>
    </xsl:variable>
    
    <xsl:variable name="nsPrefix">ifc</xsl:variable>
    <xsl:variable name="ifcSensNode" select="/ex:iso_10303_28/ex:uos//node()"/>
    
    <!-- define the doc as rdf -->
    <xsl:template match="/">
        <rdf:RDF 
            xmlns:rdf ="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
            xmlns:xsd ="http://www.w3.org/2001/XMLSchema#"    
            xmlns:ifc ="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs#"
            xmlns     ="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs#" 
            xml:base ="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs-instances"
            xmlns:ex  ="urn:iso10303-28:ex#" 
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance#">   
            
            <rdf:Description
                rdf:about="{$resourceURL}">  
                <!-- for debug -->
                Current resource : <xsl:value-of select="$refLocal"/> 
                <xsl:apply-templates></xsl:apply-templates>
            </rdf:Description>
        </rdf:RDF>     
    </xsl:template>
    
    <xsl:template name="noEx" match="/ex:iso_10303_28/ex:uos|/ex:uos"/>
    
    <!-- transforming attributes into elements with adding rdf:ID -->
    <!-- if attrName is "id" or "ref" they remain attributes as "rdf:ID" with the same value -->
    <xsl:template name="attrIntoProp" match="*" priority="1">
        <xsl:element name="{name()}"  namespace="{$refLocal}">
            <xsl:for-each select="@*">                
                <xsl:choose>
                    <xsl:when test="(name()='id')">
                        <xsl:attribute name="rdf:ID">
                            <xsl:value-of select="."/>
                        </xsl:attribute>
                    </xsl:when>
                    <xsl:when test="(name()='ref')">
                        <xsl:attribute name="rdf:ID">
                            <xsl:value-of select="."/>
                        </xsl:attribute>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:element name="{name()}">
                            <xsl:value-of select="."/>
                        </xsl:element> 
                    </xsl:otherwise>
                </xsl:choose>               
            </xsl:for-each>
            <xsl:apply-templates/>
        </xsl:element>
        
    </xsl:template>
    
    <!-- transforming attributes into elements >
    <xsl:template name="attrIntoProp" match="*" priority="2">
        <xsl:element name="{name()}">
            <xsl:for-each select="@*">
                <xsl:element name="{name()}">
                    <xsl:value-of select="."/>
                </xsl:element>
            </xsl:for-each>
            <xsl:apply-templates/>
        </xsl:element>        
    </xsl:template-->
    
    <!-- transformation to declare properties as minuscule -->
    <xsl:template match="*[not(starts-with(name(.),'Ifc'))]" name="toMinuscule" priority="2">
        <xsl:element name="{$nsPrefix}:{translate(name(),'ABCDEFGHIJKLMNOPQRSTUVWXYZÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞ', 'abcdefghijklmnopqrstuvwxyzàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþ')}">
            <xsl:apply-templates/>
        </xsl:element>        
    </xsl:template>
    
    <!-- Copy ALL remaining elements, putting them in a namespace. -->
    <xsl:template match="/ex:iso_10303_28/ex:uos//*">
        <xsl:apply-templates select="toMinuscule"/>
        <xsl:element name="{$nsPrefix}:{name()}">
            <xsl:apply-templates select="@*|node()"/>
        </xsl:element>
    </xsl:template>
    
    <!-- Copy  elements from the EXTRACTION list, putting them in a namespace. >
        <xsl:template match="*" name="useful">
        <xsl:apply-templates select="toMinuscule"/>
        <xsl:variable name="keyConcept" select="document('queryConcepts.xml')//concept"/>
        <xsl:for-each select="$keyConcept">
        <xsl:variable name="valueConcept" select="."/>
        <xsl:element name="{$nsPrefix}:{$keyConcept}">
        <xsl:value-of select="$valueConcept"/>
        </xsl:element>                           
        </xsl:for-each>
        <xsl:apply-templates select="@*|node()"/>
        </xsl:template-->
    
    <!-- ALT Copy  elements from the EXTRACTION list, putting them in a namespace. >
        <xsl:template match="*" name="useful">
        <xsl:variable name="valueConcept" select="document('queryConcepts.xml')//concept"/>
        <xsl:if test="$valueConcept">
        <xsl:element name="{$nsPrefix}:node()">
        <xsl:value-of select="."/>
        </xsl:element>  
        </xsl:if>
        
        <xsl:apply-templates select="@*|node()"/>
        </xsl:template-->
    
</xsl:stylesheet>
