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
    
    <!-- resourceURL must be = ifcLoadedFile HOW? given as parameter -->
    
    <xsl:variable name="resourceURL">
        <xsl:text>E:/fev2008/IFCdata/testIfc1.xml</xsl:text>
    </xsl:variable>
    
    <xsl:variable name="keyConcept" select="document('queryConcepts.xml')//concept"/>
    <xsl:variable name="thisDoc" select="document('testIfc1.xml')//ex:iso_10303_28/ex:uos/*"/>

    <xsl:template match="/">
        <xsl:element name="IfcExtracted">
            <xsl:attribute name="id">
                <xsl:value-of select="$resourceURL"/>
            </xsl:attribute>
            <!-- list useful concepts -->
            the useful concepts are as follows : 
            <xsl:for-each select="$keyConcept">
                <xsl:variable name="valueConcept" select="normalize-space(.)"/>
                current useful is <xsl:value-of select="$valueConcept"/>             
                <xsl:for-each select="$thisDoc">
                    <!--current THIS node is <xsl:value-of select="name()"/-->
                    <xsl:variable name="currentName" select="name()"/>
                    <!--$valueConcept <xsl:value-of select="$valueConcept"/>
                        node name is <xsl:value-of select="$currentName"/--> 
                    <xsl:variable name="isEqual" select="normalize-space($valueConcept)=normalize-space($currentName)"/>
                    <!-- are they equal? <xsl:value-of select="$isEqual"/-->
                    <xsl:choose>
                        <xsl:when test="$isEqual">
                            <xsl:copy-of select="."/>
                            <!--xsl:copy-of select="node()"/-->
                        </xsl:when>
                        <xsl:otherwise>
                            <!--not equal !!!-->
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:for-each>
            </xsl:for-each>
        </xsl:element>      
    </xsl:template>

    

</xsl:stylesheet>

