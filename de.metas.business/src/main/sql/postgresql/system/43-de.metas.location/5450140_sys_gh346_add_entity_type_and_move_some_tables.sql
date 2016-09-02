-- 02.09.2016 14:46
-- URL zum Konzept
INSERT INTO AD_EntityType (AD_Client_ID,AD_EntityType_ID,AD_Org_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,ModelPackage,Name,Processing,Updated,UpdatedBy,WebUIServletListenerClass) VALUES (0,540203,0,TO_TIMESTAMP('2016-09-02 14:46:50','YYYY-MM-DD HH24:MI:SS'),100,'entoty type for everything related to country, arey, location (not locator!), address etc','de.metas.location','Y','Y','de.metas.location.model','de.metas.location','N',TO_TIMESTAMP('2016-09-02 14:46:50','YYYY-MM-DD HH24:MI:SS'),100,NULL)
;

-- 02.09.2016 14:47
-- URL zum Konzept
UPDATE AD_Table SET EntityType='de.metas.location',Updated=TO_TIMESTAMP('2016-09-02 14:47:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=540383
;

-- 02.09.2016 14:47
-- URL zum Konzept
UPDATE AD_Table SET EntityType='de.metas.location',Updated=TO_TIMESTAMP('2016-09-02 14:47:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=540384
;

-- 02.09.2016 14:47
-- URL zum Konzept
UPDATE AD_Table SET IsChangeLog='Y',Updated=TO_TIMESTAMP('2016-09-02 14:47:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=540384
;

-- 02.09.2016 14:47
-- URL zum Konzept
UPDATE AD_Table SET IsChangeLog='Y',Updated=TO_TIMESTAMP('2016-09-02 14:47:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=540383
;

-- 02.09.2016 14:47
-- URL zum Konzept
UPDATE AD_Index_Column SET EntityType='de.metas.location',Updated=TO_TIMESTAMP('2016-09-02 14:47:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Index_Column_ID=540408
;

-- 02.09.2016 14:48
-- URL zum Konzept
UPDATE AD_Index_Column SET EntityType='de.metas.location',Updated=TO_TIMESTAMP('2016-09-02 14:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Index_Column_ID=540409
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Element SET Description='Gültig ab inklusiv (erster Tag)',Updated=TO_TIMESTAMP('2016-09-02 14:49:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=617
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=617
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Column SET ColumnName='ValidFrom', Name='Gültig ab', Description='Gültig ab inklusiv (erster Tag)', Help='"Gültig ab" bezeichnet den ersten Tag eines Gültigkeitzeitraumes.' WHERE AD_Element_ID=617
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Process_Para SET ColumnName='ValidFrom', Name='Gültig ab', Description='Gültig ab inklusiv (erster Tag)', Help='"Gültig ab" bezeichnet den ersten Tag eines Gültigkeitzeitraumes.', AD_Element_ID=617 WHERE UPPER(ColumnName)='VALIDFROM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Process_Para SET ColumnName='ValidFrom', Name='Gültig ab', Description='Gültig ab inklusiv (erster Tag)', Help='"Gültig ab" bezeichnet den ersten Tag eines Gültigkeitzeitraumes.' WHERE AD_Element_ID=617 AND IsCentrallyMaintained='Y'
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Field SET Name='Gültig ab', Description='Gültig ab inklusiv (erster Tag)', Help='"Gültig ab" bezeichnet den ersten Tag eines Gültigkeitzeitraumes.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=617) AND IsCentrallyMaintained='Y'
;


-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Element SET Help='"Gültig bis" bezeichnet den letzten Tag eines Gültigkeitzeitraumes.',Updated=TO_TIMESTAMP('2016-09-02 14:49:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=618
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=618
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Column SET ColumnName='ValidTo', Name='Gültig bis', Description='Gültig bis inklusiv (letzter Tag)', Help='"Gültig bis" bezeichnet den letzten Tag eines Gültigkeitzeitraumes.' WHERE AD_Element_ID=618
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Process_Para SET ColumnName='ValidTo', Name='Gültig bis', Description='Gültig bis inklusiv (letzter Tag)', Help='"Gültig bis" bezeichnet den letzten Tag eines Gültigkeitzeitraumes.', AD_Element_ID=618 WHERE UPPER(ColumnName)='VALIDTO' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Process_Para SET ColumnName='ValidTo', Name='Gültig bis', Description='Gültig bis inklusiv (letzter Tag)', Help='"Gültig bis" bezeichnet den letzten Tag eines Gültigkeitzeitraumes.' WHERE AD_Element_ID=618 AND IsCentrallyMaintained='Y'
;

-- 02.09.2016 14:49
-- URL zum Konzept
UPDATE AD_Field SET Name='Gültig bis', Description='Gültig bis inklusiv (letzter Tag)', Help='"Gültig bis" bezeichnet den letzten Tag eines Gültigkeitzeitraumes.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=618) AND IsCentrallyMaintained='Y'
;

UPDATE AD_Column SET EntityType='de.metas.location' WHERE AD_Table_ID IN (540383, 540384);

-- 02.09.2016 14:51
-- URL zum Konzept
UPDATE AD_Window SET EntityType='de.metas.location',Updated=TO_TIMESTAMP('2016-09-02 14:51:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=540149
;

-- 02.09.2016 14:52
-- URL zum Konzept
UPDATE AD_Tab SET EntityType='de.metas.location',Updated=TO_TIMESTAMP('2016-09-02 14:52:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=540411
;

-- 02.09.2016 14:52
-- URL zum Konzept
UPDATE AD_Tab SET EntityType='de.metas.location',Updated=TO_TIMESTAMP('2016-09-02 14:52:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=540410
;

UPDATE AD_Field SET EntityType='de.metas.location',Updated=TO_TIMESTAMP('2016-09-02 14:52:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tabl_ID IN (540411, 540410);
