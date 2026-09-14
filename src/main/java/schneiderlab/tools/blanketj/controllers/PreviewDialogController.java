package schneiderlab.tools.blanketj.controllers;

import ij.ImagePlus;
import schneiderlab.tools.blanketj.models.PreviewDialogModel;
import schneiderlab.tools.blanketj.uicomponents.MainDialog;
import schneiderlab.tools.blanketj.uicomponents.PreviewDialog;
import schneiderlab.tools.blanketj.uicomponents.events.ActionMouseWheelSpinnerInteger;
import schneiderlab.tools.blanketj.uicomponents.events.previewdialogevents.ActionButtonCancel;
import schneiderlab.tools.blanketj.uicomponents.events.previewdialogevents.ActionButtonConfirmParameter;
import schneiderlab.tools.blanketj.uicomponents.events.previewdialogevents.ActionButtonProcess;

import java.nio.file.Path;

public class PreviewDialogController {
    private final PreviewDialog previewDialogView;
    private final MainDialog mainDialogView;
    private final PreviewDialogModel model;
    private final ImagePlus outputImage;
    private final boolean hasChannels;
    private final int refChannelIdx;
    private final Path inputImagePath;
    private final ImagePlus inputImage;

    public PreviewDialogController(PreviewDialog previewDialogView,
                                   PreviewDialogModel model,
                                   Path inputImagePath,
                                   MainDialog mainDialogView,
                                   boolean hasChannels,
                                   int refChannelIdx,
                                   ImagePlus outputImage,
                                   ImagePlus inputImage) {
        this.previewDialogView = previewDialogView;
        this.mainDialogView = mainDialogView;
        this.model = model;
        this.outputImage = outputImage;
        this.inputImage= inputImage;
        this.hasChannels= hasChannels;
        this.refChannelIdx= refChannelIdx;
        this.inputImagePath= inputImagePath;
//        outputImage.show();
        // add basic event listener to the dialog
        // MouseWheel action event for spinner
        previewDialogView.getSpinner1Stiffeness()
                .addMouseWheelListener(new ActionMouseWheelSpinnerInteger(previewDialogView.getSpinner1Stiffeness()));
        previewDialogView.getSpinner2FilterSize()
                .addMouseWheelListener(new ActionMouseWheelSpinnerInteger(previewDialogView.getSpinner2FilterSize()));
        previewDialogView.getSpinner3Offset()
                .addMouseWheelListener(new ActionMouseWheelSpinnerInteger(previewDialogView.getSpinner3Offset()));
        previewDialogView.getSpinner4Depth()
                .addMouseWheelListener(new ActionMouseWheelSpinnerInteger(previewDialogView.getSpinner4Depth()));

        // add actionEvent for confirm parameter button
        previewDialogView.getButton1Parameter().addActionListener(new ActionButtonConfirmParameter(mainDialogView,previewDialogView));
        // add actionEvent for cancel button
        previewDialogView.getButton5Cancel().addActionListener(new ActionButtonCancel(previewDialogView));
        // add actionEvent for processing image
        previewDialogView.getButton4StartProcess().addActionListener(new ActionButtonProcess(previewDialogView,
                outputImage,
                hasChannels,
                refChannelIdx,
                inputImagePath,
                inputImage));
    }


}
