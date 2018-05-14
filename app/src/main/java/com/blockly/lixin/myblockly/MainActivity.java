package com.blockly.lixin.myblockly;

import android.support.annotation.NonNull;

import com.google.blockly.android.AbstractBlocklyActivity;
import com.google.blockly.android.codegen.CodeGenerationRequest;
import com.google.blockly.android.codegen.LoggingCodeGeneratorCallback;
import com.google.blockly.model.DefaultBlocks;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AbstractBlocklyActivity  {
    private static final String TAG = "MainActivity";

    // Add custom blocks to this list.
    private static final List<String> BLOCK_DEFINITIONS = DefaultBlocks.getAllBlockDefinitions();
    private static final List<String> JAVASCRIPT_GENERATORS = Arrays.asList(
            // Custom block generators go here. Default blocks are already included.
            // TODO(#99): Include Javascript defaults when other languages are supported.
    );

    /**
    * LogginCodeGeneratorCallback implements CodeGenerationRequest.CodeGeneratorCallback
    * the key method is onFinishCodeGeneration(String generatedCode).
    * for this logging callback, it simple displays the generated code in a Toast, it does not
    * execute the program.
    *
    */
    CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback =
        new LoggingCodeGeneratorCallback(this, TAG);


    // -------- implement abstract methods --------

    @NonNull
    @Override
    protected String getToolboxContentsXmlPath() {
        return "default/toolbox.xml";
    }

    @NonNull
    @Override
    protected List<String> getBlockDefinitionsJsonPaths() {
        return BLOCK_DEFINITIONS;
    }

    /**
    * return the file path to the JavaScript file generators. Called from onRunCode().
    * onRunCode () is called when the user selects the 'Run' command option. Internally
    * it calls:
    *   void requestCodeGeneration(
                getBlockGeneratorLanguage(),    // language definition
                getBlockDefinitionsJsonPaths(), // block definition
                getGeneratorsJsPaths(),         // JS generator path
                getCodeGenerationCallback()     // generate code, it is string
                );
    */
    @NonNull
    @Override
    protected List<String> getGeneratorsJsPaths() {
        return JAVASCRIPT_GENERATORS;
    }

    /**
    * Returns a generation callback to use for the most recently requested "Run" action. Called from onRunCode()
    */
    @NonNull
    @Override
    protected CodeGenerationRequest.CodeGeneratorCallback getCodeGenerationCallback() {
        // Uses the same callback for every generation call.
        return mCodeGeneratorCallback;
    }


    // not abstract methdd but required
    @Override
    protected void onInitBlankWorkspace() {
        // Initialize available variable names.
        getController().addVariable("item");
    }


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    */
}
