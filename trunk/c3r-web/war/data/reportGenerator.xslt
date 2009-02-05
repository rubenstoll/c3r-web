<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:cos="http://www.inria.fr/acacia/corese#"
    xmlns:sparql='http://www.w3.org/2005/sparql-results#' 
    >
    <xsl:output method="html" encoding="UTF-8"/>
  
    <xsl:variable name="nsPrefix">cos</xsl:variable>
    
    <xsl:template match="/">
        <html xmlns='http://www.w3.org/2005/sparql-results#'>
            <head>
                <title>Non Conformity Report	</title>
                <link href="../report.css" rel="stylesheet" type="text/css" />
            </head>  
            <body >                
                
                <div class="box1">  <h1>Non Conformity Report</h1>  </div>
                <div class="box2">
                    <div style="margin-left: 2 em;">No projection means compliance of the construction project to the set of chosen queries	</div>
                </div>
                <h2>General info </h2> 
                <xsl:text>These rules checked the conformity of the</xsl:text> 
                <xsl:apply-templates select="//variable/@name"/>
                <p>  
                    <a href="link_to_construction_project">Your construction project</a>
                    does not contain enough information for checking.  
                </p> 
                <xsl:text>As the result of checking </xsl:text>                    
                <xsl:apply-templates select="//cos:info"/> 
                <xsl:text>are found. Those are reasons of non-conformity </xsl:text>  
  
                <xsl:apply-templates select="//sparql:uri"/>     
            </body>
        </html>             
    </xsl:template>
  
    
    <xsl:template match="cos:info">
            <xsl:value-of select = "substring-after(., 'for')"/>   
    </xsl:template> 
    
    <xsl:template match="@name">
        <xsl:text>this rule is applied to the element</xsl:text>
        <xsl:value-of select="."/>
    </xsl:template>
    
    <xsl:template match="sparql:uri">
        <p>
            <xsl:value-of select = "substring-after(., '#')"/> 
        </p>
    </xsl:template> 
    
    <!-- aucun resultat est produit - pour eliminer la copie des resultats indesirables -->
    <xsl:template match="cos:tquery | sparql"/>
    
    <xsl:template match="binding">
        <xsl:for-each select="uri">
            <xsl:value-of select = "substring-after(., '#')"/> 
        </xsl:for-each>
    </xsl:template> 
                   
</xsl:stylesheet>
