<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"	
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" 
    xmlns:xlink="http://www.w3.org/1999/xlink#"
    xmlns:ifc="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs#"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance#"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema#"   
    xmlns:ex="urn:iso10303-28:ex">
    <xsl:output method="xml" encoding="UTF-8"/>

    <xsl:template match="/">            
                <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:variable name="valueAttribute" select="."/>
        
    <!-- transforming attributes into elements -->
    <!-- if attrName is "id" or "ref" they remain attributes as "rdf:ID" with the same value -->
    <xsl:template name="attrIntoProp" match="*">
        <xsl:element name="{name()}">
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
      
    <!-- working attrIntoElem
        xsl:template name="attrIntoProp" match="*">
        <xsl:element name="{name()}">
            <xsl:for-each select="@*">
                <xsl:element name="{name()}">
                    <xsl:value-of select="."/>
                </xsl:element>                
            </xsl:for-each>
            <xsl:apply-templates/>
        </xsl:element>
        
    </xsl:template-->
</xsl:stylesheet>
